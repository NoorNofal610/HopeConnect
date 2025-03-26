package com.example.hopeconnectt.Services;


import com.example.hopeconnectt.Models.Entity.Orphanage;
import com.example.hopeconnectt.Reposotires.OrphanageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrphanageService {
    @Autowired
    private OrphanageRepository orphanageRepository;

    public List<Orphanage> getAllOrphanages() {
        return orphanageRepository.findAll();
    }

    public Optional<Orphanage> getOrphanageById(Long id) {
        return orphanageRepository.findById(id);
    }

    public List<Orphanage> getVerifiedOrphanages(boolean verifiedStatus) {
        return orphanageRepository.findByVerifiedStatus(verifiedStatus);
    }

    public List<Orphanage> searchOrphanagesByLocation(String location) {
        return orphanageRepository.findByLocationContaining(location);
    }

    public Orphanage saveOrphanage(Orphanage orphanage) {
        return orphanageRepository.save(orphanage);
    }

    public void deleteOrphanage(Long id) {
        orphanageRepository.deleteById(id);
    }
}