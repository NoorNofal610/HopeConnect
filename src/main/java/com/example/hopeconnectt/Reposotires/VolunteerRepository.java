package com.example.hopeconnectt.Reposotires;



import com.example.hopeconnectt.Models.Entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findByEmail(String email);
    List<Volunteer> findBySkillsContaining(String skill);
    List<Volunteer> findByAvailabilityContaining(String availability);
}