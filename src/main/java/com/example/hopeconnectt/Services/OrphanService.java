package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Entity.Orphan;
import com.example.hopeconnectt.Reposotires.OrphanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrphanService {
    @Autowired
    private OrphanRepository orphanRepository;

    public List<Orphan> getAllOrphans() {
        return orphanRepository.findAll();
    }

    public Optional<Orphan> getOrphanById(Long id) {
        return orphanRepository.findById(id);
    }

    public Orphan saveOrphan(Orphan orphan) {
        return orphanRepository.save(orphan);
    }

    public void deleteOrphan(Long id) {
        orphanRepository.deleteById(id);
    }

    
}