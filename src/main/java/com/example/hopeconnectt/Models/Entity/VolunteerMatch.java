package com.example.hopeconnectt.Models.Entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "volunteer_matches")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerMatch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "volunteer_id", nullable = false) // Explicit column name
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "orphanage_id", nullable = false) // Explicit column name
    private Orphanage orphanage;

    @Column(nullable = false)
    private String serviceType; // e.g., "Teaching", "Cooking", "Cleaning"

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String status; // e.g., "Pending", "Approved", "Completed", "Cancelled"
}