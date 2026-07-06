package com.example.bms_jul_2026.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDto {
    private Long userId;
    private Long showId; // can be skipped as we can extract this from the showSeatIds
    private List<Long> showSeatIds;
}
