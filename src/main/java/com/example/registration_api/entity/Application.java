package com.example.registration_api.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private PropertyDetails propertyDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private PrimaryResident primaryResident;

    @OneToOne(cascade = CascadeType.ALL)
    private EmploymentDetails employmentDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private EmergencyContact emergencyContact;

    private boolean termsAccepted;

    // Getters and Setters
}