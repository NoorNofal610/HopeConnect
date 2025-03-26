package com.example.hopeconnectt.Reposotires;


import com.example.hopeconnectt.Models.Entity.Orphan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrphanRepository extends JpaRepository<Orphan, Long> {
    Optional<Orphan> findById(Long id);
    List<Orphan> findByOrphanageId(Long orphanageId);
    List<Orphan> findByAgeBetween(int minAge, int maxAge);
}