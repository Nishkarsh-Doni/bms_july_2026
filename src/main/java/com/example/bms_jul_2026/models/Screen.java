package com.example.bms_jul_2026.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name;
    @OneToMany
    private List<Seat> seats;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Feature> features;
}

/*
JPA creates a separate join table to store the list if features for each screen

screen_features

screen_id feature_id
1           0
1           1
2           0


screens
id screen_name .....

features
IMAX 0

  1             M
screen  ---- features
 */
