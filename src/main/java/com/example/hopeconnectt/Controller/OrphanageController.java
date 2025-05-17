package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.DTO.OrphanageDTO;



import com.example.hopeconnectt.Models.Entity.Orphanage;
import com.example.hopeconnectt.Services.OrphanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public ResponseEntity<Orphanage> getOrphanageById(@PathVariable Long id) {
    Orphanage orphanage = orphanageService.getOrphanageById(id); // No Optional here
    return ResponseEntity.ok(orphanage);
}


    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<OrphanageDTO>> getOrphanagesByName(
            @PathVariable String name) {
        return ResponseEntity.ok(orphanageService.getOrphanagesByName(name));
    }

    @GetMapping("/by-location/{location}")
    public ResponseEntity<List<OrphanageDTO>> getOrphanagesByLocation(
            @PathVariable String location) {
        List<OrphanageDTO> result = orphanageService.getOrphanagesByLocation(location);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-verified/{status}")
    public ResponseEntity<List<OrphanageDTO>> getOrphanagesByVerificationStatus(
            @PathVariable boolean status) {
        return ResponseEntity.ok(orphanageService.getOrphanagesByVerificationStatus(status));
    }

///////////////////

    @PostMapping
    public Orphanage createOrphanage(@RequestBody Orphanage orphanage) {
        return orphanageService.saveOrphanage(orphanage);
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrphanage(@PathVariable Long id) {
        try {
            if (orphanageService.existsById(id)) {
                orphanageService.deleteOrphanage(id);
                return ResponseEntity.ok("Orphanage with ID " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Orphanage with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting orphanage: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
public ResponseEntity<String> updateOrphanage(
        @PathVariable Long id,
        @RequestBody Orphanage orphanageDetails) {
    try {
        return ResponseEntity.ok("Orphanage with ID " + id + " updated successfully");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error updating orphanage: " + e.getMessage());
    }
}


}
