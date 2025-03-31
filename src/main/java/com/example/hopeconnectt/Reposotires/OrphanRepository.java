package com.example.hopeconnectt.Reposotires;


import com.example.hopeconnectt.Models.Entity.Orphan;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrphanRepository extends JpaRepository<Orphan, Long> {
    @EntityGraph(attributePaths = {"orphanage"})
    List<Orphan> findAll();
    
    @EntityGraph(attributePaths = {"orphanage"})
    List<Orphan> findByOrphanageId(Long orphanageId);
    
    @EntityGraph(attributePaths = {"orphanage"})
    List<Orphan> findByAgeBetween(Integer minAge, Integer maxAge);
    
    @EntityGraph(attributePaths = {"orphanage"})
    List<Orphan> findByEducationStatus(String educationStatus);
    
    @EntityGraph(attributePaths = {"orphanage"})
    List<Orphan> findByGender(Orphan.Gender gender);
}