package com.example.bms_jul_2026.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String seatNumber;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    @Column(name = "seat_row")
    private Integer rowNumber;
    @Column(name = "seat_col")
    private Integer columnNumber;
}
