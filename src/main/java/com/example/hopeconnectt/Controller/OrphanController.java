package com.example.hopeconnectt.Controller;


import com.example.hopeconnectt.DTO.OrphanDTO;
import com.example.hopeconnectt.Models.Entity.Orphan;
import com.example.hopeconnectt.Services.OrphanService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orphans")
@RequiredArgsConstructor
public class OrphanController {
    private final OrphanService orphanService;

    @GetMapping
    public ResponseEntity<List<OrphanDTO>> getAllOrphans() {
        return ResponseEntity.ok(orphanService.getAllOrphans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrphanDTO> getOrphanById(@PathVariable Long id) {
        return ResponseEntity.ok(orphanService.getOrphanById(id));
    }

    @PostMapping
    public ResponseEntity<Orphan> createOrphan(
            @RequestBody Orphan orphan,
            @RequestParam Long orphanageId) {
        return ResponseEntity.ok(orphanService.createOrphan(orphan, orphanageId));
    }

@PutMapping("/update/{id}")
public ResponseEntity<String> updateOrphan(
        @PathVariable Long id,
        @RequestBody Orphan orphanDetails) {
    try {
        Orphan updatedOrphan = orphanService.updateOrphan(id, orphanDetails);
        return ResponseEntity.ok("Orphan with ID " + id + " updated successfully");
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error updating orphan: " + e.getMessage());
    }
}

    @DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteOrphan(@PathVariable Long id) {
    try {
        if (orphanService.existsById(id)) {
            orphanService.deleteOrphan(id);
            return ResponseEntity.ok("Orphan with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Orphan with ID " + id + " not found");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error deleting orphan: " + e.getMessage());
    }
}

//////////////////////////
    @GetMapping("/by-orphanage/{orphanageId}")
    public ResponseEntity<List<OrphanDTO>> getOrphansByOrphanage(
            @PathVariable Long orphanageId) {
        return ResponseEntity.ok(orphanService.getOrphansByOrphanage(orphanageId));
    }

    @GetMapping("/by-age")
    public ResponseEntity<List<OrphanDTO>> getOrphansByAgeRange(
            @RequestParam Integer minAge,
            @RequestParam Integer maxAge) {
        return ResponseEntity.ok(orphanService.getOrphansByAgeRange(minAge, maxAge));
    }

    @GetMapping("/by-education")
    public ResponseEntity<List<OrphanDTO>> getOrphansByEducationStatus(
            @RequestParam String educationStatus) {
        return ResponseEntity.ok(orphanService.getOrphansByEducationStatus(educationStatus));
    }

    @GetMapping("/by-gender/{gender}")
    public ResponseEntity<List<OrphanDTO>> getOrphansByGender(
            @PathVariable Orphan.Gender gender) {
        return ResponseEntity.ok(orphanService.getOrphansByGender(gender));
    }
}