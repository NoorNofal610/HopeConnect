package com.example.hopeconnectt.Reposotires;


import com.example.hopeconnectt.Models.Entity.Orphanage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrphanageRepository extends JpaRepository<Orphanage, Long> {
    Optional<Orphanage> findById(Long id);
    List<Orphanage> findByVerifiedStatus(boolean verifiedStatus);
    List<Orphanage> findByLocationContaining(String location);
    List<Orphanage> findByNameContaining(String name);
    boolean existsById(Long id);

    
    List<Orphanage> findByName(String name);
    List<Orphanage> findByLocation(String location);
    
   
}
