package com.example.bms_jul_2026.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
// Ticket/Booking made by a User for a Show
public class Booking extends BaseModel {
    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    @OneToMany
    private List<ShowSeat> showSeats;
    @OneToMany
    private List<Payment> payments;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private double amount;
    private Date bookedAt;
}
