package com.example.registration_api.controller;

import com.example.registration_api.entity.User;
import com.example.registration_api.service.UserService;
import com.example.registration_api.dto.LoginRequest;
import com.example.registration_api.dto.LoginResponse; // Ensure you import your new DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            logger.info("User registered successfully: {}", registeredUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            logger.error("Email address already exists: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email address already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword())
                    .map(user -> {
                        logger.info("User logged in successfully: {}", loginRequest.getEmail());
                        LoginResponse loginResponse = new LoginResponse(user.getEmail(), user.getRole(),
                                "Login successful", user.getFull_name());
                        return ResponseEntity.ok(loginResponse);
                    })
                    .orElseGet(() -> {
                        logger.warn("Failed login attempt for email: {}", loginRequest.getEmail());
                        LoginResponse errorResponse = new LoginResponse(null, null, "Invalid credentias", null);
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
                    });
        } catch (Exception e) {
            logger.error("Error logging in user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error logging in: " + e.getMessage());
        }
    }

}
