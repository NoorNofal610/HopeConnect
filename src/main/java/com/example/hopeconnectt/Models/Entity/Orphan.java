package com.example.hopeconnectt.Models.Entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orphans")
public class Orphan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orphan_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    @Column(name = "education_status", nullable = false)
    private String educationStatus;

    @Column(name = "health_condition", nullable = false)
    private String healthCondition;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;
}
