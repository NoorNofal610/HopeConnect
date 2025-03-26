package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.Models.Entity.Orphan;
import com.example.hopeconnectt.Services.OrphanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orphans")
public class OrphanController {
    @Autowired
    private OrphanService orphanService;

    @GetMapping
    public List<Orphan> getAllOrphans() {
        return orphanService.getAllOrphans();
    }

    @GetMapping("/{id}")
    public Optional<Orphan> getOrphanById(@PathVariable Long id) {
        return orphanService.getOrphanById(id);
    }

    @PostMapping
    public Orphan createOrphan(@RequestBody Orphan orphan) {
        return orphanService.saveOrphan(orphan);
    }

    @DeleteMapping("/{id}")
    public void deleteOrphan(@PathVariable Long id) {
        orphanService.deleteOrphan(id);
    }
}