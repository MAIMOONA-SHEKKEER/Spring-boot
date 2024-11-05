package com.example.registration_api.dto;

public class LoginResponse {
    private String email;
    private String role;
    private String message;

    public LoginResponse(String email, String role, String message) {
        this.email = email;
        this.role = role;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
