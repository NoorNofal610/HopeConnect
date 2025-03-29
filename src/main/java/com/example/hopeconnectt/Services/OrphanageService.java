package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.DTO.OrphanageDTO;
import com.example.hopeconnectt.Models.Entity.Orphan;
import com.example.hopeconnectt.Models.Entity.Orphanage;
import com.example.hopeconnectt.Reposotires.OrphanageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrphanageService {
    @Autowired
    private OrphanageRepository orphanageRepository;

    public List<Orphanage> getAllOrphanages() {
        return orphanageRepository.findAll();
    }

    public Optional<Orphanage> getOrphanageById(Long id) {
        return orphanageRepository.findById(id);
    }

    public List<Orphanage> getVerifiedOrphanages(boolean verifiedStatus) {
        return orphanageRepository.findByVerifiedStatus(verifiedStatus);
    }

    public List<Orphanage> searchOrphanagesByLocation(String location) {
        return orphanageRepository.findByLocationContaining(location);
    }

    public Orphanage saveOrphanage(Orphanage orphanage) {
        return orphanageRepository.save(orphanage);
    }

    
    public List<Orphanage> searchOrphanagesByName(String name) {
        return orphanageRepository.findByNameContaining(name);
    }
    ///////////////////////
     // Search by name with DTO conversion
    public List<OrphanageDTO> getOrphanagesByName(String name) {
        return orphanageRepository.findByName(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Search by location with DTO conversion
    public List<OrphanageDTO> getOrphanagesByLocation(String location) {
        return orphanageRepository.findByLocationContaining(location).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Search by verification status with DTO conversion
    public List<OrphanageDTO> getOrphanagesByVerificationStatus(boolean status) {
        return orphanageRepository.findByVerifiedStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

   
    private OrphanageDTO convertToDTO(Orphanage orphanage) {
        OrphanageDTO dto = new OrphanageDTO();
        dto.setId(orphanage.getId());
        dto.setName(orphanage.getName());
        dto.setLocation(orphanage.getLocation());
        dto.setContactInfo(orphanage.getContactInfo());
        dto.setVerifiedStatus(orphanage.isVerifiedStatus());
        dto.setRating(orphanage.getRating());
        return dto; 
        // No orphan processing
    }
    ///////////////////////////

    public void deleteOrphanage(Long id) {
        orphanageRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return orphanageRepository.existsById(id);
    }

    public Orphanage updateOrphanage(Long id, Orphanage orphanageDetails) {
        return orphanageRepository.findById(id)
                .map(existingOrphanage -> {
                    // Update only non-null fields
                    if (orphanageDetails.getName() != null) {
                        existingOrphanage.setName(orphanageDetails.getName());
                    }
                    if (orphanageDetails.getLocation() != null) {
                        existingOrphanage.setLocation(orphanageDetails.getLocation());
                    }
                    if (orphanageDetails.getContactInfo() != null) {
                        existingOrphanage.setContactInfo(orphanageDetails.getContactInfo());
                    }
                    existingOrphanage.setVerifiedStatus(orphanageDetails.isVerifiedStatus());
                    existingOrphanage.setRating(orphanageDetails.getRating());
                    
                    return orphanageRepository.save(existingOrphanage);
                })
                .orElseThrow(() -> new RuntimeException("Orphanage not found with id " + id));
    }
   

}