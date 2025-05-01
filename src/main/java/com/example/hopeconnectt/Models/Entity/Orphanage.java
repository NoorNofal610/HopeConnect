package com.example.hopeconnectt.Models.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name = "orphanages")
public class Orphanage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orphanage_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(name = "contact_info", nullable = false)
    private String contactInfo;

    @Column(name = "verified_status", nullable = false)
    private boolean verifiedStatus;

    @Column(nullable = false)
    private double rating;

    
    @OneToMany(mappedBy = "orphanage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
private List<Orphan> orphans = new ArrayList<>();
@OneToMany(mappedBy = "orphanage")
    private List<Review> reviews;
}