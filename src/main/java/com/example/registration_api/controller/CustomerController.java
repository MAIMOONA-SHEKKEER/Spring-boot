package com.example.registration_api.controller;

import com.example.registration_api.entity.Customer;
import com.example.registration_api.payload.ApiResponse;
import com.example.registration_api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(service.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable Long id) {
        return service.getCustomerById(id)
                .map(customer -> new ResponseEntity<>(new ApiResponse("Customer found", true, customer), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new ApiResponse("Customer not found", false, null), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = service.saveCustomer(customer);
        return new ResponseEntity<>(new ApiResponse("Customer created successfully", true, savedCustomer),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(
            @PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return service.getCustomerById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setPhone(updatedCustomer.getPhone());
                    existingCustomer.setEmail(updatedCustomer.getEmail());
                    Customer savedCustomer = service.saveCustomer(existingCustomer);
                    return new ResponseEntity<>(new ApiResponse("Customer updated successfully", true, savedCustomer),
                            HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(new ApiResponse("Customer not found", false, null), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) {
        if (service.getCustomerById(id).isPresent()) {
            service.deleteCustomer(id);
            return new ResponseEntity<>(new ApiResponse("Customer deleted successfully", true, null),
                    HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new ApiResponse("Customer not found", false, null), HttpStatus.NOT_FOUND);
        }
    }
}
