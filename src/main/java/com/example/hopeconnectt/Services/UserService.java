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

    public User registerUser(RegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // Plain text (for demo only)
        user.setRole(request.getRole()); // This must not be null
        // ... set other fields ...
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        return userRepository.save(user);
    }
}