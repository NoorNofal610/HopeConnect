package com.example.hopeconnectt.Models.Entity;

import com.example.hopeconnectt.Models.Enumes.ReviewStatus;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "reviewId"
)
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    
   // In Review.java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "donor_id")
@JsonBackReference("donor-reviews")  // Prevents infinite loop
private Donor donor;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "orphanage_id")
@JsonBackReference("orphanage-reviews")
private Orphanage orphanage;
    
    @Column(nullable = false)
    @Min(1) @Max(5)
    private Integer rating;
    
    @Column(length = 1000)
    private String comment;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewStatus status = ReviewStatus.ACTIVE;

    @JsonGetter("donorId")
    public Long getDonorId() {
        return donor != null ? donor.getDonorId() : null;
    }

    @JsonGetter("orphanageId")
    public Long getOrphanageId() {
        return orphanage != null ? orphanage.getId() : null;
    }
}