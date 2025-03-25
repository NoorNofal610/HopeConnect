package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Enumes.UserRole;
import com.example.hopeconnectt.Models.Entity.User;
import  com.example.hopeconnectt.Reposotires.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }
        
        return user;
    }

    public boolean hasPermission(User user, UserRole requiredRole) {
        return user.getRole() == requiredRole;
    }
}