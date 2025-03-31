package com.example.hopeconnectt.Models.Entity;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityType, Long id) {
        super(entityType + " with ID " + id + " not found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}