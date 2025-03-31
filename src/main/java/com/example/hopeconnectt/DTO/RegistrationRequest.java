package com.example.hopeconnectt.DTO;

import org.antlr.v4.runtime.misc.NotNull;

import com.example.hopeconnectt.Models.Enumes.UserRole;

public class RegistrationRequest {
    private String username;
    private String password;
    private String email;
    private String phone;
    private UserRole role;

    // Constructors
    public RegistrationRequest() {}

    public RegistrationRequest(String username, String password, String email, String phone, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
