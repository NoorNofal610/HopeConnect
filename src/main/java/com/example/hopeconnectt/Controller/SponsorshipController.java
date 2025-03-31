package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.DTO.SponsorshipDTO;
import com.example.hopeconnectt.Models.Enumes.SponsorshipStatus;
import com.example.hopeconnectt.Models.Entity.Sponsorship;
import com.example.hopeconnectt.Services.SponsorshipService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sponsorships")
@RequiredArgsConstructor
@Slf4j 
public class SponsorshipController {
    
    private final SponsorshipService sponsorshipService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createSponsorship(
            @Valid @RequestBody SponsorshipDTO request) {
        try {
            Sponsorship sponsorship = sponsorshipService.createSponsorship(
                request.getDonorId(),
                request.getOrphanId(),
                request.getStartDate(),
                request.getEndDate(),
                request.getMonthlyAmount(),
                request.getNotes()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                    "status", "success",
                    "sponsorshipId", sponsorship.getSponsorshipId(),
                    "message", "Sponsorship created successfully"
                ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                Map.of(
                    "status", "error",
                    "message", e.getMessage()
                ));
        }
    }
    
    @GetMapping("/donor/{donorId}")
public ResponseEntity<?> getByDonor(@PathVariable Long donorId) {
    try {
        List<Sponsorship> sponsorships = sponsorshipService.getSponsorshipsByDonor(donorId);
        return ResponseEntity.ok(sponsorships);
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("error", e.getMessage())
        );
    } catch (Exception e) {
        log.error("Error fetching sponsorships for donor {}", donorId, e);
        return ResponseEntity.internalServerError().body(
            Map.of("error", "Failed to fetch sponsorships: " + e.getMessage())
        );
    }
}
    
    @GetMapping("/orphan/{orphanId}")
    public ResponseEntity<List<Sponsorship>> getSponsorshipsByOrphan(@PathVariable Long orphanId) {
        return ResponseEntity.ok(sponsorshipService.getSponsorshipsByOrphan(orphanId));
    }
    
    @GetMapping("/active")
    public ResponseEntity<?> getActiveSponsorships() {
        try {
            return ResponseEntity.ok(sponsorshipService.getActiveSponsorships());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                Map.of("error", "Failed to fetch active sponsorships")
            );
        }
    }
    @PatchMapping("/{sponsorshipId}/status")
    public ResponseEntity<Sponsorship> updateStatus(
            @PathVariable Long sponsorshipId,
            @RequestParam SponsorshipStatus status) {
        return ResponseEntity.ok(sponsorshipService.updateSponsorshipStatus(sponsorshipId, status));
    }
    
    @GetMapping("/between-dates")
public ResponseEntity<?> getSponsorshipsBetweenDates(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
    try {
        if (start.isAfter(end)) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Start date must be before end date")
            );
        }
        return ResponseEntity.ok(sponsorshipService.getSponsorshipsBetweenDates(start, end));
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body(
            Map.of("error", "Failed to fetch sponsorships by date range")
        );
    }
}
}