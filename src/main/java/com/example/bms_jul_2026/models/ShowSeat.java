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
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus showSeatStatus;
}

/*
   1              1
ShowSeat ------ Show
   M              1

S1S1
S2S1
S1S2
S3S2
S2S2

ShowSeat -> Show + Seat

ShowSeat --- Show -> M:1
ShowSeat --- Seat -> M:1
 */
