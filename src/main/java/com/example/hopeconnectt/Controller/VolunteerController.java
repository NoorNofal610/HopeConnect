package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.Models.Entity.Volunteer;
import com.example.hopeconnectt.Services.VolunteerService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        return ResponseEntity.ok(volunteerService.createVolunteer(volunteer));
    }

    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerService.getVolunteerById(id));
    }

    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<Volunteer>> getVolunteersBySkill(@PathVariable String skill) {
        return ResponseEntity.ok(volunteerService.getVolunteersBySkill(skill));
    }

    @GetMapping("/availability/{availability}")
    public ResponseEntity<List<Volunteer>> getVolunteersByAvailability(
            @PathVariable String availability) {
        return ResponseEntity.ok(volunteerService.getVolunteersByAvailability(availability));
    }

 @PutMapping("/{id}")
public ResponseEntity<?> updateVolunteer(
        @PathVariable Long id,
        @RequestBody Volunteer volunteerDetails) {
    try {
        Volunteer updatedVolunteer = volunteerService.updateVolunteer(id, volunteerDetails);
        return ResponseEntity.ok(updatedVolunteer);
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
                .body(Map.of("error", "Update failed: " + e.getMessage()));
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<Map<String, String>> deleteVolunteer(@PathVariable Long id) {
    try {
    volunteerService.deleteVolunteer(id);
    return ResponseEntity.ok(Map.of(
                "message", "Volunteer match deleted successfully"
            ));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
}}

