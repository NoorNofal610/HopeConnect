package com.example.hopeconnectt.Services;


import com.example.hopeconnectt.DTO.DonorDTO;
//import com.example.hopeconnectt.DTO.RegistrationRequest;
import com.example.hopeconnectt.Models.Entity.Donor;
import  com.example.hopeconnectt.Reposotires.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DonorService {
    @Autowired
    private DonorRepository donorRepository;

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Optional<Donor> getDonorById(Long id) {
        return donorRepository.findById(id);
    }

    

    public List<DonorDTO> getDonorsByName(String name) {
        return donorRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<DonorDTO> getDonorsByAddress(String address) {
        return donorRepository.findByAddressContainingIgnoreCase(address).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Keep your existing DTO conversion method
  private DonorDTO convertToDTO(Donor donor) {
    DonorDTO dto = new DonorDTO();
    dto.setDonorId(donor.getDonorId());
    dto.setName(donor.getName());
    dto.setEmail(donor.getEmail());
    dto.setPhone(donor.getPhone());
    dto.setAddress(donor.getAddress());
    dto.setDonationHistory(donor.getDonationHistory());
    dto.setPreferences(donor.getPreferences());
    return dto;
}
    /////////////////////////////////
    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return donorRepository.existsById(id);
    }
    /////////////////
    public Donor updateDonor(Long id, Donor donorDetails) {
        return donorRepository.findById(id)
                .map(existingDonor -> {
                    // Update only non-null fields
                    if (donorDetails.getName() != null) {
                        existingDonor.setName(donorDetails.getName());
                    }
                    if (donorDetails.getEmail() != null) {
                        existingDonor.setEmail(donorDetails.getEmail());
                    }
                    if (donorDetails.getPhone() != null) {
                        existingDonor.setPhone(donorDetails.getPhone());
                    }
                    if (donorDetails.getAddress() != null) {
                        existingDonor.setAddress(donorDetails.getAddress());
                    }
                    if (donorDetails.getDonationHistory() != null) {
                        existingDonor.setDonationHistory(donorDetails.getDonationHistory());
                    }
                    if (donorDetails.getPreferences() != null) {
                        existingDonor.setPreferences(donorDetails.getPreferences());
                    }
                    
                    return donorRepository.save(existingDonor);
                })
                .orElseThrow(() -> new RuntimeException("Donor not found with id " + id));
    }
}
