package com.example.registration_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class PropertyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String address;

    @NotBlank
    private String periodRequired;

    @NotBlank
    private Double rentalPayable;

    @NotBlank
    private Date occupationDate;

    private Integer numberOfOccupants;

    // Getters and Setters
}
