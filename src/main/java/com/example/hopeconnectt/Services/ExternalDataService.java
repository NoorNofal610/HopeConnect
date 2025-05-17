package com.example.hopeconnectt.Services;


import com.example.hopeconnectt.DTO.ExternalDataDTO;
import com.example.hopeconnectt.Models.Entity.ExternalData;
import com.example.hopeconnectt.Reposotires.ExternalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExternalDataService {

    private final ExternalDataRepository repository;
    private final RestTemplate restTemplate;

    private static final String EXTERNAL_API_URL = "https://example.com/api/data"; // Replace with real API

    public ExternalDataDTO fetchExternalData() {
        // Simulate a response from an external API
        ExternalData fetched = restTemplate.getForObject(EXTERNAL_API_URL, ExternalData.class);

        if (fetched != null) {
            repository.save(fetched);
            return mapToDTO(fetched);
        }

        throw new RuntimeException("Failed to fetch data from external API");
    }

    public List<ExternalDataDTO> getAll() {
        return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<ExternalDataDTO> getById(Long id) {
        return repository.findById(id).map(this::mapToDTO);
    }

    private ExternalDataDTO mapToDTO(ExternalData entity) {
        ExternalDataDTO dto = new ExternalDataDTO();
        dto.setDataType(entity.getDataType());
        dto.setDescription(entity.getDescription());
        dto.setValue(entity.getValue());
        dto.setSource(entity.getSource());
        dto.setDate(entity.getDate());
        return dto;
    }
}
