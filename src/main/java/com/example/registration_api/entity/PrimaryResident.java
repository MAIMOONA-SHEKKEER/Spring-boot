package com.example.registration_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class PrimaryResident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String idPassportNumber;

    private String businessContact;

    @NotBlank
    private String cellContact;

    @NotBlank
    private String currentAddress;

    @NotBlank
    @Email
    private String email;

    // Getters and Setters
}