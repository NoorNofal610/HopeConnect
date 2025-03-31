package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.DTO.DonationDTO;
import com.example.hopeconnectt.Models.Entity.*;
import com.example.hopeconnectt.Reposotires.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DonationService {
    @Autowired private DonationRepository donationRepository;
    @Autowired private DonorRepository donorRepository;
    @Autowired private OrphanageRepository orphanageRepository;

    // Create donation
    public DonationDTO createDonation(DonationDTO donationDTO) {
        Donor donor = donorRepository.findById(donationDTO.getDonorId())
            .orElseThrow(() -> new EntityNotFoundException("Donor", donationDTO.getDonorId()));
        
        Orphanage orphanage = orphanageRepository.findById(donationDTO.getOrphanageId())
            .orElseThrow(() -> new EntityNotFoundException("Orphanage", donationDTO.getOrphanageId()));

        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setOrphanage(orphanage);
        donation.setAmount(donationDTO.getAmount());
        donation.setCategory(donationDTO.getCategory());
        donation.setDate(donationDTO.getDate());
        donation.setStatus(donationDTO.getStatus());

        Donation savedDonation = donationRepository.save(donation);
        return convertToDTO(savedDonation);
    }

    // Get all donations
    public List<DonationDTO> getAllDonations() {
        return donationRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get donation by ID
    public DonationDTO getDonationById(Long id) {
        return donationRepository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new EntityNotFoundException("Donation", id));
    }

    // Update donation
    // public DonationDTO updateDonation(Long id, DonationDTO donationDTO) {
    //     return donationRepository.findById(id)
    //         .map(existingDonation -> {
    //             if (donationDTO.getDonorId() != null) {
    //                 Donor donor = donorRepository.findById(donationDTO.getDonorId())
    //                     .orElseThrow(() -> new EntityNotFoundException("Donor", donationDTO.getDonorId()));
    //                 existingDonation.setDonor(donor);
    //             }
                
    //             if (donationDTO.getOrphanageId() != null) {
    //                 Orphanage orphanage = orphanageRepository.findById(donationDTO.getOrphanageId())
    //                     .orElseThrow(() -> new EntityNotFoundException("Orphanage", donationDTO.getOrphanageId()));
    //                 existingDonation.setOrphanage(orphanage);
    //             }
                
    //             if (donationDTO.getAmount() > 0) {
    //                 existingDonation.setAmount(donationDTO.getAmount());
    //             }
                
    //             if (donationDTO.getCategory() != null) {
    //                 existingDonation.setCategory(donationDTO.getCategory());
    //             }
                
    //             if (donationDTO.getDate() != null) {
    //                 existingDonation.setDate(donationDTO.getDate());
    //             }
                
    //             if (donationDTO.getStatus() != null) {
    //                 existingDonation.setStatus(donationDTO.getStatus());
    //             }
                
    //             Donation updatedDonation = donationRepository.save(existingDonation);
    //             return convertToDTO(updatedDonation);
    //         })
    //         .orElseThrow(() -> new EntityNotFoundException("Donation", id));
    // }

    // // Delete donation
    // public void deleteDonation(Long id) {
    //     if (!donationRepository.existsById(id)) {
    //         throw new EntityNotFoundException("Donation", id);
    //     }
    //     donationRepository.deleteById(id);
    // }
     public void updateDonation(Long id, DonationDTO donationDTO) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Donation", id));

        // Update only non-null fields
        if (donationDTO.getDonorId() != null) {
            // Handle donor update if needed
        }
        if (donationDTO.getOrphanageId() != null) {
            // Handle orphanage update if needed
        }
        if (donationDTO.getAmount() > 0) {
            donation.setAmount(donationDTO.getAmount());
        }
        if (donationDTO.getCategory() != null) {
            donation.setCategory(donationDTO.getCategory());
        }
        if (donationDTO.getDate() != null) {
            donation.setDate(donationDTO.getDate());
        }
        if (donationDTO.getStatus() != null) {
            donation.setStatus(donationDTO.getStatus());
        }

        donationRepository.save(donation);
    }

    public boolean existsById(Long id) {
        return donationRepository.existsById(id);
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    // Get donations by donor ID
    public List<DonationDTO> getDonationsByDonorId(Long donorId) {
        return donationRepository.findByDonor_DonorId(donorId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get donations by orphanage ID
    public List<DonationDTO> getDonationsByOrphanageId(Long orphanageId) {
        return donationRepository.findByOrphanage_Id(orphanageId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get donations by category
    public List<DonationDTO> getDonationsByCategory(String category) {
        return donationRepository.findByCategory(category).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get donations by status
    public List<DonationDTO> getDonationsByStatus(String status) {
        return donationRepository.findByStatus(status).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get donations by amount range
    public List<DonationDTO> getDonationsByAmountRange(Double minAmount, Double maxAmount) {
        if (minAmount == null && maxAmount == null) {
            return getAllDonations();
        } else if (minAmount == null) {
            return donationRepository.findByAmountLessThanEqual(maxAmount).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        } else if (maxAmount == null) {
            return donationRepository.findByAmountGreaterThanEqual(minAmount).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        } else {
            return donationRepository.findByAmountBetween(minAmount, maxAmount).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        }
    }

    // Get donations by date range
    public List<DonationDTO> getDonationsByDateRange(LocalDate startDate, LocalDate endDate) {
        return donationRepository.findByDateBetween(startDate, endDate).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Helper method to convert entity to DTO
    private DonationDTO convertToDTO(Donation donation) {
        DonationDTO dto = new DonationDTO();
        dto.setDonationId(donation.getDonationId());
        dto.setDonorId(donation.getDonor().getDonorId());
        dto.setOrphanageId(donation.getOrphanage().getId());
        dto.setAmount(donation.getAmount());
        dto.setCategory(donation.getCategory());
        dto.setDate(donation.getDate());
        dto.setStatus(donation.getStatus());
        return dto;
    }
}