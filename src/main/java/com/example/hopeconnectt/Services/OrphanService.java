package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.DTO.OrphanDTO;
import com.example.hopeconnectt.Exceptions.InvalidProfileException;
import com.example.hopeconnectt.Exceptions.OrphanNotFoundException;
import com.example.hopeconnectt.Models.Entity.Orphan;
import com.example.hopeconnectt.Models.Entity.Orphanage;
import com.example.hopeconnectt.Reposotires.OrphanRepository;
import com.example.hopeconnectt.Reposotires.OrphanageRepository;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Path;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrphanService {
    private final OrphanRepository orphanRepository;
    private final OrphanageRepository orphanageRepository;

    public List<OrphanDTO> getAllOrphans() {
        return orphanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // public OrphanDTO getOrphanById(Long id) {
    //     return orphanRepository.findById(id)
    //             .map(this::convertToDTO)
    //             .orElseThrow(() -> new RuntimeException("Orphan not found"));
    // }
    public OrphanDTO getOrphanById(Long id) {
    Orphan orphan = orphanRepository.findById(id)
            .orElseThrow(() -> new OrphanNotFoundException("Orphan with ID " + id + " not found."));

    // Let's say you want to validate the profile before converting it to DTO
    if (orphan.getName() == null || orphan.getAge() == null) {
        throw new InvalidProfileException("Orphan profile is incomplete or invalid.");
    }

    return convertToDTO(orphan);
}


    public Orphan createOrphan(Orphan orphan, Long orphanageId) {
        Orphanage orphanage = orphanageRepository.findById(orphanageId)
                .orElseThrow(() -> new RuntimeException("Orphanage not found"));
        orphan.setOrphanage(orphanage);
        return orphanRepository.save(orphan);
    }

    // public Orphan updateOrphan(Long id, Orphan orphanDetails) {
    //     Orphan orphan = orphanRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Orphan not found"));
        
    //     orphan.setName(orphanDetails.getName());
    //     orphan.setAge(orphanDetails.getAge());
    //     orphan.setGender(orphanDetails.getGender());
    //     orphan.setEducationStatus(orphanDetails.getEducationStatus());
    //     orphan.setHealthCondition(orphanDetails.getHealthCondition());
        
    //     return orphanRepository.save(orphan);
    // }
  public Orphan updateOrphan(Long id, Orphan orphanDetails) {
    return orphanRepository.findById(id)
            .map(existingOrphan -> {
                existingOrphan.setName(orphanDetails.getName());
                existingOrphan.setAge(orphanDetails.getAge());
                existingOrphan.setGender(orphanDetails.getGender());
                existingOrphan.setEducationStatus(orphanDetails.getEducationStatus());
                existingOrphan.setHealthCondition(orphanDetails.getHealthCondition());
                return orphanRepository.save(existingOrphan);
            })
            .orElseThrow(() -> new OrphanNotFoundException("Orphan with ID " + id + " not found."));
}


    public void deleteOrphan(Long id) {
    if (!orphanRepository.existsById(id)) {
        throw new OrphanNotFoundException("Orphan with ID " + id + " not found.");
    }
    orphanRepository.deleteById(id);
}


    public List<OrphanDTO> getOrphansByOrphanage(Long orphanageId) {
        return orphanRepository.findByOrphanageId(orphanageId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OrphanDTO> getOrphansByAgeRange(Integer minAge, Integer maxAge) {
        return orphanRepository.findByAgeBetween(minAge, maxAge).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OrphanDTO> getOrphansByEducationStatus(String educationStatus) {
        return orphanRepository.findByEducationStatus(educationStatus).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OrphanDTO> getOrphansByGender(Orphan.Gender gender) {
        return orphanRepository.findByGender(gender).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrphanDTO convertToDTO(Orphan orphan) {
        OrphanDTO dto = new OrphanDTO();
        dto.setId(orphan.getId());
        dto.setName(orphan.getName());
        dto.setAge(orphan.getAge());
        dto.setGender(orphan.getGender().toString());
        dto.setEducationStatus(orphan.getEducationStatus());
        dto.setHealthCondition(orphan.getHealthCondition());
        
        if (orphan.getOrphanage() != null) {
            OrphanDTO.OrphanageDTO orphanageDTO = new OrphanDTO.OrphanageDTO();
            orphanageDTO.setId(orphan.getOrphanage().getId());
            orphanageDTO.setName(orphan.getOrphanage().getName());
            orphanageDTO.setLocation(orphan.getOrphanage().getLocation());
            dto.setOrphanage(orphanageDTO);
        }
        
        return dto;
    }
    public boolean existsById(Long id) {
        return orphanRepository.existsById(id);
    }
   
@Transactional
public void updatePhotoPath(Long orphanId, String photoPath) {
    Orphan orphan = orphanRepository.findById(orphanId)
            .orElseThrow(() -> new OrphanNotFoundException("Orphan with ID " + orphanId + " not found."));
    orphan.setPhotoPath(photoPath);
    orphanRepository.save(orphan);
}




}