package com.example.hopeconnectt.DTO;



import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String role;
}