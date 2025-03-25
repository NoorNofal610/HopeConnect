package com.example.hopeconnectt.Services;


import com.example.hopeconnectt.DTO.RegistrationRequest;
import com.example.hopeconnectt.Models.Entity.User;
import  com.example.hopeconnectt.Reposotires.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
/// try to implement the registerUser method
/// noor 
    public User registerUser(RegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // Plain text password (not secure)
        user.setRole(request.getRole());
        return userRepository.save(user);
    }
}
