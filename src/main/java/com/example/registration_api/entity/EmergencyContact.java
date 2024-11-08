package com.example.registration_api.entity;

import jakarta.persistence.*;

@Entity
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstContactName;
    private String firstContactRelationship;
    private String firstContactPhone;

    private String secondContactName;
    private String secondContactRelationship;
    private String secondContactPhone;

    // Getters and Setters
}