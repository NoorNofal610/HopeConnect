package com.example.hopeconnectt.Exceptions;

public class OrphanNotFoundException extends RuntimeException {
    public OrphanNotFoundException(String message) {
        super(message);
    }
}