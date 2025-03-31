package com.example.hopeconnectt.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SponsorshipDTO {
    @NotNull(message = "Donor ID is required")
    private Long donorId;
    
    @NotNull(message = "Orphan ID is required")
    private Long orphanId;
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    private LocalDate endDate;
    private Double monthlyAmount;
    private String notes;
}