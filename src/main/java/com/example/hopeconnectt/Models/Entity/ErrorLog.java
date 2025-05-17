package com.example.hopeconnectt.Models.Entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "errorlogs")

public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime timestamp;

    // Optional: Add fields like exceptionType, stackTrace, etc.

    // Constructors
    public ErrorLog() {}

    public ErrorLog(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and Setters
}

