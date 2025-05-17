package com.example.hopeconnectt.Exceptions;

public class InvalidDonationAmountException extends RuntimeException {
    public InvalidDonationAmountException(String message) {
        super(message);
    }
}
