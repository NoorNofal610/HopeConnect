package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Entity.Volunteer;
import com.example.hopeconnectt.Reposotires.VolunteerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;

    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
    }

    public List<Volunteer> getVolunteersBySkill(String skill) {
        return volunteerRepository.findBySkillsContaining(skill);
    }

    public List<Volunteer> getVolunteersByAvailability(String availability) {
        return volunteerRepository.findByAvailabilityContaining(availability);
    }

   @Transactional
public Volunteer updateVolunteer(Long id, Volunteer volunteerDetails) {
    Volunteer volunteer = volunteerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Volunteer not found with id: " + id));
    
    // Partial update - only modify provided fields
    if (volunteerDetails.getName() != null) {
        volunteer.setName(volunteerDetails.getName());
    }
    if (volunteerDetails.getPhone() != null) {
        volunteer.setPhone(volunteerDetails.getPhone());
    }
    if (volunteerDetails.getSkills() != null) {
        volunteer.setSkills(volunteerDetails.getSkills());
    }
    // Add other fields similarly
    
    return volunteerRepository.save(volunteer);
}

    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}