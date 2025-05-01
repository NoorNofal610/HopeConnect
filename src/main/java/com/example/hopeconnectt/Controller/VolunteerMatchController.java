package com.example.hopeconnectt.Controller;


import com.example.hopeconnectt.DTO.VolunteerMatchDTO;
import com.example.hopeconnectt.Models.Entity.VolunteerMatch;
import com.example.hopeconnectt.Services.VolunteerMatchService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/volunteer-matches")
@RequiredArgsConstructor
public class VolunteerMatchController {
    
    private final VolunteerMatchService matchService;

    @PostMapping
    public ResponseEntity<?> createMatch(
            @Valid @RequestBody VolunteerMatchDTO request) {
        
        try {
            VolunteerMatch match = matchService.createMatch(request); // Pass the entire DTO
            return ResponseEntity.status(HttpStatus.CREATED).body(match);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to create match: " + e.getMessage()));
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllMatches() {
        try {
            List<VolunteerMatchDTO> matches = matchService.getAllMatches();
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to fetch matches: " + e.getMessage()));
        }
    }
    @GetMapping("/volunteer/{volunteerId}")
    public ResponseEntity<?> getByVolunteer(@PathVariable Long volunteerId) {
        try {
            List<VolunteerMatchDTO> matches = matchService.getMatchesByVolunteer(volunteerId);
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to fetch matches: " + e.getMessage()));
        }
    }

    @GetMapping("/orphanage/{orphanageId}")
    public ResponseEntity<List<VolunteerMatch>> getByOrphanage(
            @PathVariable Long orphanageId) {
        return ResponseEntity.ok(matchService.getMatchesByOrphanage(orphanageId));
    }

    @PatchMapping("/{matchId}/status")
    public ResponseEntity<VolunteerMatch> updateStatus(
            @PathVariable Long matchId,
            @RequestParam String status) {
        return ResponseEntity.ok(matchService.updateStatus(matchId, status));
    }
    @DeleteMapping("/{matchId}")
    public ResponseEntity<Map<String, String>> deleteMatch(@PathVariable Long matchId) {
        try {
            matchService.deleteMatch(matchId);
            return ResponseEntity.ok(Map.of(
                "message", "Volunteer match deleted successfully",
                "deletedId", matchId.toString()
            ));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
}