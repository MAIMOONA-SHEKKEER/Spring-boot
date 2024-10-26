package com.example.registration_api.service;

import com.example.registration_api.entity.PropertyManager;
import com.example.registration_api.repository.PropertyManagerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyManagerService {

    private final PropertyManagerRepository repository;

    public PropertyManagerService(PropertyManagerRepository repository) {
        this.repository = repository;
    }

    // Get all property managers
    public List<PropertyManager> getAllPropertyManagers() {
        return repository.findAll();
    }

    // Get a specific property manager by ID
    public PropertyManager getPropertyManagerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property Manager not found"));
    }

    // Create a new property manager
    public PropertyManager createPropertyManager(PropertyManager manager) {
        return repository.save(manager);
    }

    // Update an existing property manager by ID
    public PropertyManager updatePropertyManager(Long id, PropertyManager manager) {
        PropertyManager existingManager = getPropertyManagerById(id); // Reuse method for consistency

        // Update fields
        existingManager.setName(manager.getName());
        existingManager.setPhone(manager.getPhone());
        existingManager.setEmail(manager.getEmail());

        return repository.save(existingManager);
    }

    // Delete a property manager by ID
    public void deletePropertyManager(Long id) {
        PropertyManager existingManager = getPropertyManagerById(id); // Reuse method for consistency
        repository.delete(existingManager);
    }
}
