package com.example.hopeconnectt.Models.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "volunteers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long volunteerId;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    @Pattern(regexp = "^\\+?[0-9\\s-]{10,}$", message = "Invalid phone format")
    private String phone;

    @Column(length = 1000)
    private String skills; // Could be comma-separated list

    @Column(length = 500)
    private String availability; // e.g., "Weekends, Evenings"

    @Column(length = 500)
    private String preferences; // e.g., "Teaching, Outdoor activities"
}