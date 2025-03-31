package com.example.hopeconnectt.DTO;

import lombok.Data;
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class OrphanageDTO {
    private Long id;
    private String name;
    private String location;
    private String contactInfo;
    private boolean verifiedStatus;
    private double rating;
}
