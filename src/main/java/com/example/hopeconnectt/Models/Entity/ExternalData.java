package com.example.hopeconnectt.Models.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ExternalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataType;    // health, education, emergency, etc.
    private String description; // Brief info
    private String value;       // Actual content or value
    private String source;      // Source name (e.g., UNICEF)
    private String date;        // Data collection date
}
