package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Entity.Donor;
import com.example.hopeconnectt.Models.Entity.EmergencyCampaign;
import com.example.hopeconnectt.Reposotires.DonorRepository;
import com.example.hopeconnectt.Reposotires.EmergencyCampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmergencyCampaignService {

    private final EmergencyCampaignRepository repository;
    private final DonorRepository donorRepository;
    private final EmailService emailService;

    public List<EmergencyCampaign> getAllCampaigns() {
        return repository.findAll();
    }

    public Optional<EmergencyCampaign> getCampaignById(Long id) {
        return repository.findById(id);
    }

    public EmergencyCampaign createCampaign(EmergencyCampaign campaign) {
        EmergencyCampaign savedCampaign = repository.save(campaign);
        notifyEligibleDonors(savedCampaign);
        return savedCampaign;
    }

    public EmergencyCampaign updateCampaign(Long id, EmergencyCampaign updated) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setDescription(updated.getDescription());
            existing.setTargetAmount(updated.getTargetAmount());
            existing.setEndDate(updated.getEndDate());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public void deleteCampaign(Long id) {
        repository.deleteById(id);
    }

    // 🔔 Notify donors who prefer email
    public void notifyEligibleDonors(EmergencyCampaign campaign) {
        List<Donor> emailDonors = donorRepository.findByPreferencesIgnoreCaseContaining("email");

        for (Donor donor : emailDonors) {
            emailService.sendUrgentCampaignNotification(
                donor.getEmail(),
                campaign.getTitle(),
                campaign.getDescription(),
                campaign.getTargetAmount()
            );
        }
    }
}
