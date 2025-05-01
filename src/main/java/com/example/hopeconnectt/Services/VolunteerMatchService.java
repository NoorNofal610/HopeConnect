package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.DTO.VolunteerMatchDTO;

import com.example.hopeconnectt.Models.Entity.Orphanage;
import com.example.hopeconnectt.Models.Entity.Volunteer;
import com.example.hopeconnectt.Models.Entity.VolunteerMatch;
import com.example.hopeconnectt.Reposotires.VolunteerMatchRepository;
import com.example.hopeconnectt.Reposotires.VolunteerRepository;

import jakarta.persistence.EntityNotFoundException;

import com.example.hopeconnectt.Reposotires.OrphanageRepository;
import com.example.hopeconnectt.DTO.VolunteerMatchDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerMatchService {
    
    private final VolunteerMatchRepository matchRepository;
    private final VolunteerRepository volunteerRepository;
    private final OrphanageRepository orphanageRepository;

    
    @Transactional
    public VolunteerMatch createMatch(VolunteerMatchDTO request) {
        // Validation is handled by @Valid in controller
        Volunteer volunteer = volunteerRepository.findById(request.getVolunteerId())
                .orElseThrow(() -> new EntityNotFoundException("Volunteer not found with ID: " + request.getVolunteerId()));
        
        Orphanage orphanage = orphanageRepository.findById(request.getOrphanageId())
                .orElseThrow(() -> new EntityNotFoundException("Orphanage not found with ID: " + request.getOrphanageId()));
    
        return matchRepository.save(VolunteerMatch.builder()
                .volunteer(volunteer)
                .orphanage(orphanage)
                .serviceType(request.getServiceType())
                .date(request.getDate())
                .status("Pending")
                .build());
    }
    public List<VolunteerMatchDTO> getAllMatches() {
        return matchRepository.findAll().stream()
            .map(match -> {
                VolunteerMatchDTO dto = new VolunteerMatchDTO();
                dto.setMatchId(match.getMatchId());
                dto.setServiceType(match.getServiceType());
                dto.setDate(match.getDate());
                dto.setStatus(match.getStatus());
                
                // If you need related entity IDs/names
                if (match.getVolunteer() != null) {
                    dto.setVolunteerId(match.getVolunteer().getVolunteerId());
                    dto.setVolunteerName(match.getVolunteer().getName());
                }
                if (match.getOrphanage() != null) {
                    dto.setOrphanageId(match.getOrphanage().getId());
                    dto.setOrphanageName(match.getOrphanage().getName());
                }
                
                return dto;
            })
            .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<VolunteerMatchDTO> getMatchesByVolunteer(Long volunteerId) {
        return matchRepository.findByVolunteer_VolunteerId(volunteerId).stream()
                .map(match -> {
                    VolunteerMatchDTO dto = new VolunteerMatchDTO();
                    dto.setMatchId(match.getMatchId());
                    dto.setServiceType(match.getServiceType());
                    dto.setDate(match.getDate());
                    dto.setStatus(match.getStatus());
                    dto.setVolunteerId(match.getVolunteer().getVolunteerId());
                    dto.setOrphanageId(match.getOrphanage().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    public List<VolunteerMatch> getMatchesByOrphanage(Long orphanageId) {
        return matchRepository.findByOrphanage_Id(orphanageId);
    }

    @Transactional
    public VolunteerMatch updateStatus(Long matchId, String newStatus) {
        VolunteerMatch match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));
        match.setStatus(newStatus);
        return matchRepository.save(match);
    }
    @Transactional
public void deleteMatch(Long matchId) {
    if (!matchRepository.existsById(matchId)) {
        throw new EntityNotFoundException("Match not found with ID: " + matchId);
    }
    matchRepository.deleteByMatchId(matchId);
}
}