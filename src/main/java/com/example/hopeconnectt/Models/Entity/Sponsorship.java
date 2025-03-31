package com.example.hopeconnectt.Models.Entity;

import com.example.hopeconnectt.Models.Enumes.SponsorshipStatus;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sponsorships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "sponsorshipId"
)
public class Sponsorship {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sponsorshipId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    @JsonBackReference("donor-sponsorships")
    private Donor donor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orphan_id", nullable = false)
    @JsonBackReference("orphan-sponsorships")
    private Orphan orphan;
    
    @Column(nullable = false)
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SponsorshipStatus status;
    
    @Column(length = 1000)
    private String notes;
    
    @Column(columnDefinition = "DECIMAL(10,2)") // Fixed for MySQL
    private Double monthlyAmount;

    @JsonGetter("donorId")
    public Long getDonorId() {
        return donor != null ? donor.getDonorId() : null;
    }

    @JsonGetter("orphanId")
    public Long getOrphanId() {
        return orphan != null ? orphan.getId() : null;
    }
}