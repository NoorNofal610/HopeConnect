package com.example.hopeconnectt.DTO;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LogisticsDTO {
    private Long logisticsId;
    
    @NotNull(message = "Donation ID is required")
    private Long donationId;
    
    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;
    
    @NotBlank(message = "Delivery location is required")
    private String deliveryLocation;
    
    @NotBlank(message = "Status is required")
    private String status;
}
