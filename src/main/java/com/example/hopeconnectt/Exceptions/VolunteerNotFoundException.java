package com.example.hopeconnectt.Exceptions;

public class VolunteerNotFoundException extends RuntimeException {
    public VolunteerNotFoundException(String message) {
        super(message);
    }
}