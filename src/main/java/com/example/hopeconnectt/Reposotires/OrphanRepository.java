package com.example.hopeconnectt.Reposotires;


import com.example.hopeconnectt.Models.Entity.Orphan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrphanRepository extends JpaRepository<Orphan, Long> {
    
    List<Orphan> findByOrphanageId(Long orphanageId);
    
    List<Orphan> findByAgeBetween(Integer minAge, Integer maxAge);
    
    List<Orphan> findByEducationStatus(String educationStatus);
    
    List<Orphan> findByGender(Orphan.Gender gender);
}
// import com.example.hopeconnectt.Models.Entity.Orphan;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface OrphanRepository extends JpaRepository<Orphan, Long> {
//     List<Orphan> findByOrphanageId(Long orphanageId);
// }