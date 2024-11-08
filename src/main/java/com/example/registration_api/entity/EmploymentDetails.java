package com.example.registration_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class EmploymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String employmentType;

    private String currentEmployer;

    private String position;

    private String lengthOfService;

    private Double grossSalary;

    private Double additionalIncome;

    // Getters and Setters
}