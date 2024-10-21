package com.example.registration_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.registration_api.entity.PropertyManager;
import com.example.registration_api.repository.PropertyManagerRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/property-managers")
public class PropertyManagerController {

    private final PropertyManagerRepository repository;

    public PropertyManagerController(PropertyManagerRepository repository) {
        this.repository = repository;
    }

    // Get all property managers
    @GetMapping
    public ResponseEntity<List<PropertyManager>> getAllPropertyManagers() {
        List<PropertyManager> managers = repository.findAll();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    // Get a specific property manager by ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyManager> getPropertyManagerById(@PathVariable Long id) {
        PropertyManager manager = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property Manager not found"));
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    // Create a new property manager
    @PostMapping
    public ResponseEntity<String> createPropertyManager(@RequestBody PropertyManager manager) {
        repository.save(manager); // Save the new property manager to the repository
        return new ResponseEntity<>("Property Manager created successfully", HttpStatus.CREATED);
    }

    // Update an existing property manager by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePropertyManager(@PathVariable Long id, @RequestBody PropertyManager manager) {
        PropertyManager existingManager = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property Manager not found"));

        // Update fields
        existingManager.setName(manager.getName());
        existingManager.setPhone(manager.getPhone());
        existingManager.setEmail(manager.getEmail());

        repository.save(existingManager);
        return new ResponseEntity<>("Property Manager updated successfully", HttpStatus.OK);
    }

    // Delete a property manager by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropertyManager(@PathVariable Long id) {
        PropertyManager existingManager = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property Manager not found"));

        repository.delete(existingManager);
        return new ResponseEntity<>("Property Manager deleted successfully", HttpStatus.OK);
    }
}
