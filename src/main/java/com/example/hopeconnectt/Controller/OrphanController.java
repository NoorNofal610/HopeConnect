package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.Models.Entity.Orphan;
import com.example.hopeconnectt.Services.OrphanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orphans")
@RequiredArgsConstructor
public class OrphanController {
    
    private final OrphanService orphanService;

    @GetMapping
    public ResponseEntity<List<Orphan>> getAllOrphans() {
        return ResponseEntity.ok(orphanService.getAllOrphans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orphan> getOrphanById(@PathVariable Long id) {
        return ResponseEntity.ok(orphanService.getOrphanById(id));
    }

    // @PostMapping
    // public ResponseEntity<Orphan> createOrphan(
    //         @RequestBody Orphan orphan,
    //         @RequestParam Long orphanageId) {
    //     return ResponseEntity.ok(orphanService.createOrphan(orphan, orphanageId));
    // }
    @PostMapping
    public ResponseEntity<Orphan> createOrphan(
            @RequestBody Orphan orphan,
            @RequestParam Long orphanageId) {
        return ResponseEntity.ok(orphanService.createOrphan(orphan, orphanageId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orphan> updateOrphan(
            @PathVariable Long id,
            @RequestBody Orphan orphanDetails) {
        return ResponseEntity.ok(orphanService.updateOrphan(id, orphanDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrphan(@PathVariable Long id) {
        orphanService.deleteOrphan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-orphanage/{orphanageId}")
    public ResponseEntity<List<Orphan>> getOrphansByOrphanage(
            @PathVariable Long orphanageId) {
        return ResponseEntity.ok(orphanService.getOrphansByOrphanage(orphanageId));
    }

    @GetMapping("/by-age")
    public ResponseEntity<List<Orphan>> getOrphansByAgeRange(
            @RequestParam Integer minAge,
            @RequestParam Integer maxAge) {
        return ResponseEntity.ok(orphanService.getOrphansByAgeRange(minAge, maxAge));
    }

    @GetMapping("/by-education")
    public ResponseEntity<List<Orphan>> getOrphansByEducationStatus(
            @RequestParam String educationStatus) {
        return ResponseEntity.ok(orphanService.getOrphansByEducationStatus(educationStatus));
    }

    @GetMapping("/by-gender/{gender}")
    public ResponseEntity<List<Orphan>> getOrphansByGender(
            @PathVariable Orphan.Gender gender) {
        return ResponseEntity.ok(orphanService.getOrphansByGender(gender));
    }
}
//  import com.example.hopeconnectt.DTO.OrphanRequest;
// import com.example.hopeconnectt.Models.Entity.Orphan;
// import com.example.hopeconnectt.Services.OrphanService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/orphans")
// public class OrphanController {
    
//     private final OrphanService orphanService;

//     @Autowired
//     public OrphanController(OrphanService orphanService) {
//         this.orphanService = orphanService;
//     }

//     @PostMapping
//     public Orphan createOrphan(@RequestBody OrphanRequest request) {
//         Orphan orphan = new Orphan();
//         orphan.setName(request.getName());
//         orphan.setAge(request.getAge());
//         orphan.setGender(request.getGender());
//         orphan.setEducationStatus(request.getEducationStatus());
//         orphan.setHealthCondition(request.getHealthCondition());
//         orphan.setOrphanageId(request.getOrphanageId()); // Set the orphanage ID
        
//         return orphanService.saveOrphan(orphan);
//     }

//     @GetMapping
//     public List<Orphan> getAllOrphans() {
//         return orphanService.getAllOrphans();
//     }

//     @GetMapping("/{id}")
//     public Optional<Orphan> getOrphanById(@PathVariable Long id) {
//         return orphanService.getOrphanById(id);
//     }

//     @GetMapping("/by-orphanage/{orphanageId}")
//     public List<Orphan> getOrphansByOrphanage(@PathVariable Long orphanageId) {
//         return orphanService.getOrphansByOrphanage(orphanageId);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteOrphan(@PathVariable Long id) {
//         orphanService.deleteOrphan(id);
//     }
// }