package com.example.hopeconnectt.DTO;
import com.example.hopeconnectt.Models.Enumes.CampaignStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmergencyCampaignDTO {
    private Long campaignId;
    private String title;
    private String description;
    private Double targetAmount;
    private Double raisedAmount;
    private CampaignStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

