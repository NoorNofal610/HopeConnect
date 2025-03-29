package com.example.hopeconnectt.DTO;


import lombok.Data;

@Data
public class DonorDTO {
    private Long donorId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String donationHistory;
    private String preferences;

    // No-args constructor
    public DonorDTO() {}

    // All-args constructor
    public DonorDTO(Long donorId, String name, String email, String phone, 
                   String address, String donationHistory, String preferences) {
        this.donorId = donorId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.donationHistory = donationHistory;
        this.preferences = preferences;
    }
}