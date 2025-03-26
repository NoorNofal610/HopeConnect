package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Entity.Orphan;
import com.example.hopeconnectt.Reposotires.OrphanRepository;
import com.example.hopeconnectt.Reposotires.OrphanageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrphanService {
    private final OrphanRepository orphanRepository;
    private final OrphanageRepository orphanageRepository;

    @Autowired
    public OrphanService(OrphanRepository orphanRepository, 
                        OrphanageRepository orphanageRepository) {
        this.orphanRepository = orphanRepository;
        this.orphanageRepository = orphanageRepository;
    }

    public Orphan saveOrphan(Orphan orphan) {
        // Verify orphanage exists
        if (orphan.getOrphanageId() == null || 
            !orphanageRepository.existsById(orphan.getOrphanageId())) {
            throw new RuntimeException("Orphanage not found with ID: " + orphan.getOrphanageId());
        }
        return orphanRepository.save(orphan);
    }

    public List<Orphan> getAllOrphans() {
        return orphanRepository.findAll();
    }

    public Optional<Orphan> getOrphanById(Long id) {
        return orphanRepository.findById(id);
    }

    public List<Orphan> getOrphansByOrphanage(Long orphanageId) {
        if (!orphanageRepository.existsById(orphanageId)) {
            throw new RuntimeException("Orphanage not found with ID: " + orphanageId);
        }
        return orphanRepository.findByOrphanageId(orphanageId);
    }

    public void deleteOrphan(Long id) {
        if (!orphanRepository.existsById(id)) {
            throw new RuntimeException("Orphan not found with ID: " + id);
        }
        orphanRepository.deleteById(id);
    }
}