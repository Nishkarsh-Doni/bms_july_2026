package com.example.bms_jul_2026.controllers;


import com.example.bms_jul_2026.dtos.CreateBookingRequestDto;
import com.example.bms_jul_2026.dtos.CreateBookingResponseDto;
import com.example.bms_jul_2026.dtos.ResponseStatus;
import com.example.bms_jul_2026.exceptions.ShowNotFoundException;
import com.example.bms_jul_2026.exceptions.UserNotFoundException;
import com.example.bms_jul_2026.models.Booking;
import com.example.bms_jul_2026.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto) throws UserNotFoundException, ShowNotFoundException {
       return null;
    }
}
