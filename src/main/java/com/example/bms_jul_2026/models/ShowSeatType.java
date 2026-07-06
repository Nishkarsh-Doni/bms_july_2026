package com.example.bms_jul_2026.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel {
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    private double price;
}

/*
ShowSeatType -> Show + SeatType

ShowSeatType ---- Show -> M:1
ShowSeatType ----- SeatType -> M:1
 */
