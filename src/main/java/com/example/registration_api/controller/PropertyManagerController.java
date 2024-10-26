package com.example.registration_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.registration_api.entity.PropertyManager;
import com.example.registration_api.service.PropertyManagerService;

import java.util.List;

@RestController
@RequestMapping("/api/property-managers")
@CrossOrigin(origins = "http://localhost:3000")
public class PropertyManagerController {

    private final PropertyManagerService service;

    public PropertyManagerController(PropertyManagerService service) {
        this.service = service;
    }

    // Get all property managers
    @GetMapping
    public ResponseEntity<List<PropertyManager>> getAllPropertyManagers() {
        List<PropertyManager> managers = service.getAllPropertyManagers();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    // Get a specific property manager by ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyManager> getPropertyManagerById(@PathVariable Long id) {
        PropertyManager manager = service.getPropertyManagerById(id);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    // Create a new property manager
    @PostMapping
    public ResponseEntity<PropertyManager> createPropertyManager(@RequestBody PropertyManager manager) {
        PropertyManager createdManager = service.createPropertyManager(manager);
        return new ResponseEntity<>(createdManager, HttpStatus.CREATED);
    }

    // Update an existing property manager by ID
    @PutMapping("/{id}")
    public ResponseEntity<PropertyManager> updatePropertyManager(
            @PathVariable Long id, @RequestBody PropertyManager manager) {
        PropertyManager updatedManager = service.updatePropertyManager(id, manager);
        return new ResponseEntity<>(updatedManager, HttpStatus.OK);
    }

    // Delete a property manager by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropertyManager(@PathVariable Long id) {
        service.deletePropertyManager(id);
        return new ResponseEntity<>("Property Manager deleted successfully", HttpStatus.OK);
    }
}
