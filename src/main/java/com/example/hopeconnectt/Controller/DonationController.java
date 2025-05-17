package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.DTO.DonationDTO;
import com.example.hopeconnectt.Services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {
    @Autowired 
    private DonationService donationService;

    // Create donation
    @PostMapping
    public ResponseEntity<DonationDTO> createDonation(@RequestBody DonationDTO donationDTO) {
        DonationDTO createdDonation = donationService.createDonation(donationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDonation);
    }

    // Get all donations
    @GetMapping
    public ResponseEntity<List<DonationDTO>> getAllDonations() {
        return ResponseEntity.ok(donationService.getAllDonations());
    }

    // Get donation by ID
    @GetMapping("/{id}")
    public ResponseEntity<DonationDTO> getDonationById(@PathVariable Long id) {
        return ResponseEntity.ok(donationService.getDonationById(id));
    }

    

    // Get donations by donor ID
    @GetMapping("/donor-id/{donorId}")
    public ResponseEntity<List<DonationDTO>> getDonationsByDonorId(@PathVariable Long donorId) {
        return ResponseEntity.ok(donationService.getDonationsByDonorId(donorId));
    }

    // Get donations by orphanage ID
    @GetMapping("/orphanage-id/{orphanageId}")
    public ResponseEntity<List<DonationDTO>> getDonationsByOrphanageId(@PathVariable Long orphanageId) {
        return ResponseEntity.ok(donationService.getDonationsByOrphanageId(orphanageId));
    }

    // Get donations by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<DonationDTO>> getDonationsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(donationService.getDonationsByCategory(category));
    }

    // Get donations by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<DonationDTO>> getDonationsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(donationService.getDonationsByStatus(status));
    }

    // Get donations by amount range
    @GetMapping("/amount")
    public ResponseEntity<List<DonationDTO>> getDonationsByAmountRange(
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount) {
        return ResponseEntity.ok(donationService.getDonationsByAmountRange(minAmount, maxAmount));
    }

    // Get donations by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<DonationDTO>> getDonationsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(donationService.getDonationsByDateRange(startDate, endDate));
    }

    //////////////////////////
    /// // Update donation
    // @PutMapping("/update/{id}")
    // public ResponseEntity<DonationDTO> updateDonation(
    //         @PathVariable Long id, 
    //         @RequestBody DonationDTO donationDTO) {
    //     return ResponseEntity.ok(donationService.updateDonation(id, donationDTO));
    // }
    @PutMapping("/update/{id}")  // This matches your error URL
    public ResponseEntity<String> updateDonation(
            @PathVariable Long id,
            @RequestBody DonationDTO donationDTO) {
        try {
            donationService.updateDonation(id, donationDTO);
            return ResponseEntity.ok("Donation with ID " + id + " updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating donation: " + e.getMessage());
        }
    }

    // Delete donation
    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
    //     donationService.deleteDonation(id);
    //     return ResponseEntity.noContent().build();
    // }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDonation(@PathVariable Long id) {
        try {
            if (donationService.existsById(id)) {
                donationService.deleteDonation(id);
                return ResponseEntity.ok("Donation with ID " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Donation with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting donation: " + e.getMessage());
        }
    }
}