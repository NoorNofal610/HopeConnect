package com.example.hopeconnectt.DTO;


import lombok.Data;

@Data
public class ExternalDataDTO {
    private String dataType;
    private String description;
    private String value;
    private String source;
    private String date;
}
