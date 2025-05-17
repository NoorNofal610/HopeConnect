package com.example.hopeconnectt.Services;
import com.example.hopeconnectt.DTO.RegistrationRequest;
import com.example.hopeconnectt.Exceptions.UserNotFoundException;
import com.example.hopeconnectt.Models.Entity.User;
import  com.example.hopeconnectt.Reposotires.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

   private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User registerUser(RegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encode password
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        
        return userRepository.save(user);
    }
    // NEW: Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Uses built-in JpaRepository method
    }

    // NEW: Delete user by ID
    public void deleteUser(Long userId) {
    if (!userRepository.existsById(userId)) {
        throw new UserNotFoundException("User not found with id: " + userId);
    }
    userRepository.deleteById(userId);
}


   public User getUserById(Long userId) {
    return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
}

    // In UserService.java
public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
}
}