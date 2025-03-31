package com.example.hopeconnectt.Models.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "donors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donorId;

    private String name;
    private String email;
    private String phone;
    private String address;
    private String donationHistory;
    private String preferences;
}

