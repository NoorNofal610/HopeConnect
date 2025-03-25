package com.example.hopeconnectt.Controller;
import com.example.hopeconnectt.DTO.RegistrationRequest;
import com.example.hopeconnectt.Models.Entity.User;
import com.example.hopeconnectt.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 import com.example.hopeconnectt.DTO.LoginRequest;
import com.example.hopeconnectt.Services.AuthenticationService;      

// @RestController


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authService;

    public AuthController(UserService userService, AuthenticationService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationRequest request) {
        return userService.registerUser(request);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        User user = authService.authenticate(request.getUsername(), request.getPassword());
        return "Welcome " + user.getRole().name().toLowerCase();
    }
}