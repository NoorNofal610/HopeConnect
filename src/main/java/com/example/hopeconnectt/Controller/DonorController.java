package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.DTO.DonorDTO;
import com.example.hopeconnectt.Models.Entity.Donor;
import com.example.hopeconnectt.Services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/donors")
public class DonorController {
    @Autowired
    private DonorService donorService;

    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/{id}")
    public Optional<Donor> getDonorById(@PathVariable Long id) {
        return donorService.getDonorById(id);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<DonorDTO>> getDonorsByName(
            @PathVariable String name) {
        List<DonorDTO> result = donorService.getDonorsByName(name);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/by-address/{address}")
public ResponseEntity<List<DonorDTO>> getDonorsByAddress(
        @PathVariable String address) {
    return ResponseEntity.ok(donorService.getDonorsByAddress(address));
}
    
//////////////////////////////////////
    @PostMapping
    public Donor createDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }

    // @DeleteMapping("/{id}")
    // public void deleteDonor(@PathVariable Long id) {
    //     donorService.deleteDonor(id);
    // }
    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteDonor(@PathVariable Long id) {
    try {
        if (donorService.existsById(id)) {
            donorService.deleteDonor(id);
            return ResponseEntity.ok("Donor with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Donor with ID " + id + " not found");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error deleting donor: " + e.getMessage());
    }
}
///////////////////////
@PutMapping("/{id}")
public ResponseEntity<String> updateDonor(
        @PathVariable Long id,
        @RequestBody Donor donorDetails) {
    try {
        Donor updatedDonor = donorService.updateDonor(id, donorDetails);
        return ResponseEntity.ok("Donor with ID " + id + " updated successfully");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error updating donor: " + e.getMessage());
    }
}
}
