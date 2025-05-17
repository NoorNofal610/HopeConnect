package com.example.hopeconnectt.Exceptions;



import java.time.LocalDateTime;

public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;

    public ApiError(int status, String error, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.path = path;
    }

    // Getters and Setters
}
