package com.example.bms_jul_2026.services;

import com.example.bms_jul_2026.controllers.BookingController;
import com.example.bms_jul_2026.exceptions.SeatAlreadyBookedException;
import com.example.bms_jul_2026.exceptions.ShowNotFoundException;
import com.example.bms_jul_2026.exceptions.UserNotFoundException;
import com.example.bms_jul_2026.models.*;
import com.example.bms_jul_2026.repositories.BookingRepository;
import com.example.bms_jul_2026.repositories.ShowRepository;
import com.example.bms_jul_2026.repositories.ShowSeatRepository;
import com.example.bms_jul_2026.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking createBooking(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException, SeatAlreadyBookedException {
        /*
        Steps:
        1. Fetch the user info from the database
        2. Fetch the show details from the database
        3. Fetch show seats and the check the availability -> A1, A2, A3, A4(occupied), A5(occupied)
        4. Check that all the seats should be available
        5. If all the seats are available, BLOCK all the seats
        6. Create a booking object
        7. Do the payment -> create payment etc. will be handled by payment service
        8. Save the booking and return the object back to the client
         */

        Optional<User> userOptional =  userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with id: " + userId + " does not exist");
        }
        User user = userOptional.get();

        Optional<Show> showOptional = showRepository.findById(showId);
        if (showOptional.isEmpty()) {
            throw new ShowNotFoundException("Show with id: " + showId + " does not exist");
        }
        Show show = showOptional.get();

        // This locks only the specific rows
        // Other seats remain unlocked
        List<ShowSeat> showSeats = showSeatRepository.findAllByIdWithLock(showSeatIds);

        // Check if all the seats are available
        // safe because no other transaction can modify the rows
        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new SeatAlreadyBookedException("Seat " + showSeat.getSeat().getSeatNumber() + " is not available");
            }
        }

        // Block all the seats
        for (ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED); // in memory
        }

        showSeatRepository.saveAll(showSeats);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShow(show);
        booking.setPayments(new ArrayList<>());
        booking.setBookedAt(new Date());
        booking.setShowSeats(showSeats);

        booking = bookingRepository.save(booking);

        return booking;
    }
}

/*
User A -> Blocks seat A1 and A2
User B -> Tries to book B1

User B can book easily

User A -> Blocks seat B1 and B2
User B -> Tries to book B1

T1 for User A -> currently progress -> completes
T2 for User B -> wait -> Take a lock -> Throw SeatAlreadyBookedException
 */
