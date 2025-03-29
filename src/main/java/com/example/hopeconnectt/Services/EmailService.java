package com.example.hopeconnectt.Services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendLoginNotification(String toEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Successful Login Notification");
        message.setText("Dear " + username + ",\n\n" +
                "You have successfully logged in to HopeConnect.\n" +
                "If this wasn't you, please contact our support team immediately.\n\n" +
                "Best regards,\n" +
                "HopeConnect Team");
        
        mailSender.send(message);
    }
}