package com.example.hopeconnectt.Models.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Logistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logisticsId;

    @ManyToOne
    @JoinColumn(name = "donation_id", nullable = false)
    private Donation donation;

    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private String deliveryLocation;

    @Column(nullable = false)
    private String status;
}