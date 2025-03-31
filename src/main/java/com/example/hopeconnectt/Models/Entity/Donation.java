package com.example.hopeconnectt.Models.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationId;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "orphanage_id")
    private Orphanage orphanage;

    @Column
    private double amount;

    @Column
    private String category;

    @Column
    private LocalDate date;

    @Column
    private String status;
}