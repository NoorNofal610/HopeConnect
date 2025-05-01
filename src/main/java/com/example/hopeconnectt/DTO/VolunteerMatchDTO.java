package com.example.hopeconnectt.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerMatchDTO {
    private Long matchId;
    private String serviceType;
    private LocalDate date;
    private String status;
    
    // Optionally add these if you need to reference related entities
    @NotNull(message = "Volunteer ID is required")
    private Long volunteerId;
    
    @NotNull(message = "Orphanage ID is required")
    private Long orphanageId;
    private String volunteerName;
   
    private String orphanageName;
}