package com.example.hopeconnectt.Models.Entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "orphans")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orphan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orphan_id")
    private Long id;
   

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer age;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    
    @Column(name = "education_status", nullable = false)
    private String educationStatus;
    
    @Column(name = "health_condition")
    private String healthCondition;
    @Column
    private String photoPath;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;
   

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}