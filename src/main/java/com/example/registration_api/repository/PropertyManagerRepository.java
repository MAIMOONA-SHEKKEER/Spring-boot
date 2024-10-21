package com.example.registration_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.registration_api.entity.PropertyManager;

public interface PropertyManagerRepository extends JpaRepository<PropertyManager, Long> {
}
