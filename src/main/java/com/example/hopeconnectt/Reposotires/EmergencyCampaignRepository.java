package com.example.hopeconnectt.Reposotires;

// EmergencyCampaignRepository.java

import com.example.hopeconnectt.Models.Entity.EmergencyCampaign;
import com.example.hopeconnectt.Models.Enumes.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmergencyCampaignRepository extends JpaRepository<EmergencyCampaign, Long> {
    List<EmergencyCampaign> findByStatus(CampaignStatus status);
}
