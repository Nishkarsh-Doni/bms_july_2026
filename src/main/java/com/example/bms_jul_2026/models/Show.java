package com.example.bms_jul_2026.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel {
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Screen screen;
    private Date startTime;
    private Date endTime;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Feature> features;
    @OneToMany
    private List<ShowSeat> showSeats;
}
