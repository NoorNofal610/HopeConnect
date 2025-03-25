package com.example.hopeconnectt.Models.Entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import com.example.hopeconnectt.Models.Enumes.UserRole;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

   @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
 // e.g., "DONOR", "VOLUNTEER", "ADMIN"

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}