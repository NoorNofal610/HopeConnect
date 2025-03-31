package com.example.hopeconnectt.Controller;
import com.example.hopeconnectt.DTO.RegistrationRequest;
import com.example.hopeconnectt.Models.Entity.User;
import com.example.hopeconnectt.Services.UserService;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 import com.example.hopeconnectt.DTO.LoginRequest;
import com.example.hopeconnectt.Services.AuthenticationService;
import com.example.hopeconnectt.Services.EmailService;      




@RestController
@RequestMapping("/api/auth")
public class AuthController {

    ///////Another comment to test merge
    /// nooor
    private final UserService userService;
    private final AuthenticationService authService;
private final EmailService emailService;

    public AuthController(UserService userService, AuthenticationService authService,
                          EmailService emailService) {
        this.userService = userService;
        this.authService = authService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationRequest request) {
        return userService.registerUser(request);
    }
   @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest request) {
    try {
        User user = authService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Welcome " + user.getRole().name().toLowerCase() + 
                              ". A login notification has been sent to your email.");
    } catch (UsernameNotFoundException | BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
     @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
// New function to search user by ID
@GetMapping("/admin/search_user/{userId}")
public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    User user = userService.getUserById(userId);
    if (user == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
}
    @DeleteMapping("/admin/users/{userId}")
   
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " deleted successfully");
    }
    @GetMapping("/error-notification")
    public String testErrorNotification() {
        try {
            throw new RuntimeException("This is a test error");
        } catch (Exception e) {
            emailService.sendErrorNotification(e.getMessage(), 
                Arrays.toString(e.getStackTrace()));
            return "Error notification sent to admin!";
        }
    }
}