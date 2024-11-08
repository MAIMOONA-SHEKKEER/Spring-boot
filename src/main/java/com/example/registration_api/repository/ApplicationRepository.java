package com.example.registration_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registration_api.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}