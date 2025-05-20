package com.example.hopeconnectt.Controller;


import com.example.hopeconnectt.DTO.OrphanDTO;
import com.example.hopeconnectt.Models.Entity.Orphan;
import com.example.hopeconnectt.Services.OrphanService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/orphans")
@RequiredArgsConstructor
public class OrphanController {
    private final OrphanService orphanService;
    @Value("${file.upload-dir}")
private String uploadDir;

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

    /**
 * Upload a photo for a specific orphan.
 */
@PostMapping("/{id}/upload-photo")
public ResponseEntity<String> uploadOrphanPhoto(
        @PathVariable Long id,
        @RequestParam("file") MultipartFile file) {
    try {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        orphanService.updatePhotoPath(id, fileName); // Update DB

        return ResponseEntity.ok("Photo uploaded successfully");
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to upload image: " + e.getMessage());
    }
}
/**
 * Get the uploaded photo by filename.
 */
@GetMapping("/photo/{filename:.+}")
public ResponseEntity<Resource> getOrphanPhoto(@PathVariable("filename") String filename) {
    try {
        Path filePath = Paths.get("uploads").resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            String contentType = "image/jpeg"; // or detect dynamically
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    } catch (MalformedURLException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}



}