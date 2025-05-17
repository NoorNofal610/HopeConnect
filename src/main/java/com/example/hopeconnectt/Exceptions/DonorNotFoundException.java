package com.example.hopeconnectt.Exceptions;


public class DonorNotFoundException extends RuntimeException {
    public DonorNotFoundException(Long id) {
        super("Donor not found with id: " + id);
    }
}
