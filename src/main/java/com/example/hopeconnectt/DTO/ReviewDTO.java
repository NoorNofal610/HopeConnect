package com.example.hopeconnectt.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    @NotNull
    private Long donorId;
    
    @NotNull
    private Long orphanageId;
    
    @NotNull
    @Min(1) @Max(5)
    private Integer rating;
    
    private String comment;
}