package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Enumes.SponsorshipStatus;
import com.example.hopeconnectt.Models.Entity.*;
import com.example.hopeconnectt.Reposotires.*;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SponsorshipService {
    
    private final SponsorshipRepository sponsorshipRepository;
    private final DonorRepository donorRepository;
    private final OrphanRepository orphanRepository;
    
    @Transactional
public Sponsorship createSponsorship(Long donorId, Long orphanId, 
                                   LocalDate startDate, LocalDate endDate,
                                   Double monthlyAmount, String notes) {
    try {
        Donor donor = donorRepository.findById(donorId)
            .orElseThrow(() -> new EntityNotFoundException("Donor not found with id: " + donorId));
            
        Orphan orphan = orphanRepository.findById(orphanId)
            .orElseThrow(() -> new EntityNotFoundException("Orphan not found with id: " + orphanId));
            
        return sponsorshipRepository.save(Sponsorship.builder()
            .donor(donor)
            .orphan(orphan)
            .startDate(startDate)
            .endDate(endDate)
            .monthlyAmount(monthlyAmount)
            .notes(notes)
            .status(SponsorshipStatus.ACTIVE)
            .build());
    } catch (DataIntegrityViolationException e) {
        throw new RuntimeException("Database constraint violation: " + e.getMessage());
    }
}
    
@Transactional(readOnly = true)
public List<Sponsorship> getSponsorshipsByDonor(Long donorId) {
    if (!donorRepository.existsById(donorId)) {
        throw new EntityNotFoundException("Donor not found with id: " + donorId);
    }
    return sponsorshipRepository.findByDonor_DonorId(donorId);
}

@Transactional(readOnly = true)
public List<Sponsorship> getSponsorshipsByOrphan(Long orphanId) {
    if (!orphanRepository.existsById(orphanId)) {
        throw new EntityNotFoundException("Orphan not found with id: " + orphanId);
    }
    return sponsorshipRepository.findByOrphan_Id(orphanId);
}
    
    public List<Sponsorship> getActiveSponsorships() {
        return sponsorshipRepository.findByStatus(SponsorshipStatus.ACTIVE);
    }
    
    @Transactional
    public Sponsorship updateSponsorshipStatus(Long sponsorshipId, SponsorshipStatus status) {
        Sponsorship sponsorship = sponsorshipRepository.findById(sponsorshipId)
            .orElseThrow(() -> new RuntimeException("Sponsorship not found"));
        
        sponsorship.setStatus(status);
        return sponsorshipRepository.save(sponsorship);
    }
    
    public List<Sponsorship> getSponsorshipsBetweenDates(LocalDate start, LocalDate end) {
        return sponsorshipRepository.findByStartDateBetween(start, end);
    }
    // In your service:
public void deleteSponsorship(Long sponsorshipId) {
    if (!sponsorshipRepository.existsById(sponsorshipId)) {
        throw new EntityNotFoundException("Sponsorship not found");
    }
    sponsorshipRepository.deleteById(sponsorshipId);
}
    
}