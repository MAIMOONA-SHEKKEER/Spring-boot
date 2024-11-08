package com.example.registration_api.dto;

public class LoginResponse {
    private String email;
    private String role;
    private String message;
    private String name;

    public LoginResponse(String email, String role, String message, String name) {
        this.email = email;
        this.role = role;
        this.message = message;
        this.name = name;
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

    public String getFull_name() {
        return name;
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
