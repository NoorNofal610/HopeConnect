package com.example.hopeconnectt.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class DonationDTO {
    private Long donationId;
    
    @NotNull(message = "Donor ID is required")
    private Long donorId;
    
    @NotNull(message = "Orphanage ID is required")
    private Long orphanageId;
    
    @Positive(message = "Amount must be positive")
    private double amount;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    @NotBlank(message = "Status is required")
    private String status;
}