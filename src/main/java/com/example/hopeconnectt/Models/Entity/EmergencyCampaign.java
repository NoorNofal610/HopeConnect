package com.example.hopeconnectt.Models.Entity;

import com.example.hopeconnectt.Models.Enumes.CampaignStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "Emergency_Campaigns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignId;

    private String title;
    private String description;
    private Double targetAmount;
    private Double raisedAmount = 0.0;

    @Enumerated(EnumType.STRING)
    private CampaignStatus status = CampaignStatus.ACTIVE;

    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate;
}
