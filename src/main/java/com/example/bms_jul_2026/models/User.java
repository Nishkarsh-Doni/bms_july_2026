package com.example.bms_jul_2026.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "bms_user") // Why? -> We use this because "user" is a reserved keyword in most SQL DBs
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    @OneToMany
    private List<Booking> bookings;
}

/*
Create table user .....
 */
