package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.Models.Entity.Orphanage;
import com.example.hopeconnectt.Services.OrphanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orphanages")
public class OrphanageController {
    @Autowired
    private OrphanageService orphanageService;

    @GetMapping
    public List<Orphanage> getAllOrphanages() {
        return orphanageService.getAllOrphanages();
    }

    @GetMapping("/{id}")
    public Optional<Orphanage> getOrphanageById(@PathVariable Long id) {
        return orphanageService.getOrphanageById(id);
    }

    @GetMapping("/verified/{status}")
    public List<Orphanage> getOrphanagesByVerificationStatus(@PathVariable boolean status) {
        return orphanageService.getVerifiedOrphanages(status);
    }

    @GetMapping("/search")
    public List<Orphanage> searchOrphanagesByLocation(@RequestParam String location) {
        return orphanageService.searchOrphanagesByLocation(location);
    }

    @PostMapping
    public Orphanage createOrphanage(@RequestBody Orphanage orphanage) {
        return orphanageService.saveOrphanage(orphanage);
    }

    @DeleteMapping("/{id}")
    public void deleteOrphanage(@PathVariable Long id) {
        orphanageService.deleteOrphanage(id);
    }
}
