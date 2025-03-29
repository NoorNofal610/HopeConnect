package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Entity.Orphan;
import com.example.hopeconnectt.Models.Entity.Orphanage;
import com.example.hopeconnectt.Reposotires.OrphanRepository;
import com.example.hopeconnectt.Reposotires.OrphanageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrphanService {
    
    private final OrphanRepository orphanRepository;
    private final OrphanageRepository orphanageRepository;

    public List<Orphan> getAllOrphans() {
        return orphanRepository.findAll();
    }

    public Orphan getOrphanById(Long id) {
        return orphanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orphan not found"));
    }

    public Orphan createOrphan(Orphan orphan, Long orphanageId) {
        Orphanage orphanage = orphanageRepository.findById(orphanageId)
                .orElseThrow(() -> new RuntimeException("Orphanage not found"));
        orphan.setOrphanage(orphanage);
        return orphanRepository.save(orphan);
    }

    public Orphan updateOrphan(Long id, Orphan orphanDetails) {
        Orphan orphan = getOrphanById(id);
        orphan.setName(orphanDetails.getName());
        orphan.setAge(orphanDetails.getAge());
        orphan.setGender(orphanDetails.getGender());
        orphan.setEducationStatus(orphanDetails.getEducationStatus());
        orphan.setHealthCondition(orphanDetails.getHealthCondition());
        return orphanRepository.save(orphan);
    }

    public void deleteOrphan(Long id) {
        orphanRepository.deleteById(id);
    }

    public List<Orphan> getOrphansByOrphanage(Long orphanageId) {
        return orphanRepository.findByOrphanageId(orphanageId);
    }

    public List<Orphan> getOrphansByAgeRange(Integer minAge, Integer maxAge) {
        return orphanRepository.findByAgeBetween(minAge, maxAge);
    }

    public List<Orphan> getOrphansByEducationStatus(String educationStatus) {
        return orphanRepository.findByEducationStatus(educationStatus);
    }

    public List<Orphan> getOrphansByGender(Orphan.Gender gender) {
        return orphanRepository.findByGender(gender);
    }
}
// import com.example.hopeconnectt.Models.Entity.Orphan;
// import com.example.hopeconnectt.Reposotires.OrphanRepository;
// import com.example.hopeconnectt.Reposotires.OrphanageRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class OrphanService {
//     private final OrphanRepository orphanRepository;
//     private final OrphanageRepository orphanageRepository;

//     @Autowired
//     public OrphanService(OrphanRepository orphanRepository, 
//                         OrphanageRepository orphanageRepository) {
//         this.orphanRepository = orphanRepository;
//         this.orphanageRepository = orphanageRepository;
//     }

//     public Orphan saveOrphan(Orphan orphan) {
//         // Verify orphanage exists using the ID field
//         if (orphan.getOrphanageId() == null || 
//             !orphanageRepository.existsById(orphan.getOrphanageId())) {
//             throw new RuntimeException("Orphanage not found with ID: " + orphan.getOrphanageId());
//         }
//         return orphanRepository.save(orphan);
//     }

//     public List<Orphan> getAllOrphans() {
//         return orphanRepository.findAll();
//     }

//     public Optional<Orphan> getOrphanById(Long id) {
//         return orphanRepository.findById(id);
//     }

//     public List<Orphan> getOrphansByOrphanage(Long orphanageId) {
//         if (!orphanageRepository.existsById(orphanageId)) {
//             throw new RuntimeException("Orphanage not found with ID: " + orphanageId);
//         }
//         return orphanRepository.findByOrphanageId(orphanageId);
//     }

//     public void deleteOrphan(Long id) {
//         orphanRepository.deleteById(id);
//     }
// }