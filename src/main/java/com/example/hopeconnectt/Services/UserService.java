package com.example.hopeconnectt.Services;


import com.example.hopeconnectt.DTO.RegistrationRequest;
import com.example.hopeconnectt.Models.Entity.User;
import  com.example.hopeconnectt.Reposotires.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private int x;
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); 
        user.setRole(request.getRole());
        return userRepository.save(user);
    }
}
