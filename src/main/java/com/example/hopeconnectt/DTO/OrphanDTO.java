package com.example.hopeconnectt.DTO;


import lombok.Data;

@Data
public class OrphanDTO {
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String educationStatus;
    private String healthCondition;
    private OrphanageDTO orphanage;

    @Data
    public static class OrphanageDTO {
        private Long id;
        private String name;
        private String location;
    }
}