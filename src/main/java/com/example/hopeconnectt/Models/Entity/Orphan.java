package com.example.hopeconnectt.Models.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orphans")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orphan {
    
    @Id
    @Column(name = "orphan_id")  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}
// import jakarta.persistence.*;
// import lombok.Data;

// @Entity
// @Table(name = "orphans")
// public class Orphan {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long orphanId;

//     private String name;
//     private Integer age;
    
//     @Enumerated(EnumType.STRING)
//     private String gender;
    
//     private String educationStatus;
//     private String healthCondition;
    
//     @ManyToOne
//     @JoinColumn(name = "orphanage_id", referencedColumnName = "orphanage_id")
//     private Orphanage orphanage;
    
//     // Getters and setters
//     // Constructors
// }