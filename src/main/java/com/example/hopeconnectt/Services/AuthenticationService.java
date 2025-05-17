package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Enumes.UserRole;
import com.example.hopeconnectt.Exceptions.UnauthorizedAccessException;
import com.example.hopeconnectt.Models.Entity.User;
import com.example.hopeconnectt.Reposotires.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    public AuthenticationService(UserRepository userRepository, 
                               PasswordEncoder passwordEncoder,
                               JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    // @Transactional
    // public User authenticate(String username, String password) {
    //     User user = userRepository.findByUsername(username)
    //             .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
    //     if (!passwordEncoder.matches(password, user.getPassword())) {
    //         throw new BadCredentialsException("Invalid password");
    //     }
        
    //     sendLoginNotification(user);
    //     return user;
    // }
    @Transactional
public User authenticate(String username, String password) {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    
    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new UnauthorizedAccessException("Invalid password");
    }
    
    sendLoginNotification(user);
    return user;
}


    public boolean hasPermission(User user, UserRole requiredRole) {
        return user.getRole() == requiredRole;
    }

    private void sendLoginNotification(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Login Notification - HopeConnect");
            message.setText(String.format(
                "Dear %s,\n\n" +
                "You have successfully logged in to HopeConnect at %s.\n" +
                "If this wasn't you, please contact our support team immediately.\n\n" +
                "Best regards,\n" +
                "HopeConnect Team",
                user.getUsername(),
                java.time.LocalDateTime.now()
            ));
            
            mailSender.send(message);
        } catch (Exception e) {
            // Log the error but don't fail the login
            System.err.println("Failed to send login notification email: " + e.getMessage());
        }
    }
}