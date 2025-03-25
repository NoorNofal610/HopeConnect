package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.Models.Entity.Donor;
import com.example.hopeconnectt.Services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Donor createDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }

    @DeleteMapping("/{id}")
    public void deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
    }
}
