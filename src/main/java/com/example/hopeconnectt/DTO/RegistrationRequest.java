package com.example.hopeconnectt.DTO;



import org.antlr.v4.runtime.misc.NotNull;

import com.example.hopeconnectt.Models.Enumes.UserRole;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
  @NotNull
    private UserRole role;
    private String email;
    private String phone;
}