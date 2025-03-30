package com.example.hopeconnectt.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${admin.email:remaabualnaser570@gmail.com}") // Default value
    private String adminEmail;

    @Value("${app.email.sender:noreply@hopeconnect.org}")
    private String senderEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Existing login notification
    public void sendLoginNotification(String toEmail, String username) {
        SimpleMailMessage message = createBaseMessage();
        message.setTo(toEmail);
        message.setSubject("Successful Login Notification");
        message.setText(String.format("""
            Dear %s,

            You have successfully logged in to HopeConnect.
            If this wasn't you, please contact our support team immediately.

            Login Time: %s

            Best regards,
            HopeConnect Team
            """, username, java.time.LocalDateTime.now()));
        
        mailSender.send(message);
    }

    // New error notification method
    public void sendErrorNotification(String errorDetails, String stackTrace) {
        SimpleMailMessage message = createBaseMessage();
        message.setTo(adminEmail);
        message.setSubject("[URGENT] System Error Notification");
        message.setText(String.format("""
            ADMIN ALERT:

            A critical error occurred in the HopeConnect system.

            Error Details:
            %s

            Stack Trace:
            %s

            Time: %s

            Please investigate immediately.
            """, errorDetails, stackTrace, java.time.LocalDateTime.now()));
        
        mailSender.send(message);
    }

    // New general notification method
    public void sendGeneralNotification(String toEmail, String subject, String content) {
        SimpleMailMessage message = createBaseMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    private SimpleMailMessage createBaseMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        return message;
    }
}