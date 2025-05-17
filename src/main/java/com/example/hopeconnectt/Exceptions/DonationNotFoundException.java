package com.example.hopeconnectt.Exceptions;

public class DonationNotFoundException extends RuntimeException {
    public DonationNotFoundException(Long id) {
        super("Donation with ID " + id + " not found.");
    }
}

