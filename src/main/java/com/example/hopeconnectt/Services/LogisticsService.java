package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.DTO.LogisticsDTO;
import com.example.hopeconnectt.Models.Entity.*;
import com.example.hopeconnectt.Models.Entity.EntityNotFoundException;
import com.example.hopeconnectt.Reposotires.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogisticsService {
    @Autowired 
    private LogisticsRepository logisticsRepository;
    
    @Autowired
    private DonationRepository donationRepository;

    // Create logistics record
    public LogisticsDTO createLogistics(LogisticsDTO logisticsDTO) {
        Donation donation = donationRepository.findById(logisticsDTO.getDonationId())
            .orElseThrow(() -> new EntityNotFoundException("Donation", logisticsDTO.getDonationId()));

        Logistics logistics = new Logistics();
        logistics.setDonation(donation);
        logistics.setPickupLocation(logisticsDTO.getPickupLocation());
        logistics.setDeliveryLocation(logisticsDTO.getDeliveryLocation());
        logistics.setStatus(logisticsDTO.getStatus());

        Logistics savedLogistics = logisticsRepository.save(logistics);
        return convertToDTO(savedLogistics);
    }

    // Get all logistics records
    public List<LogisticsDTO> getAllLogistics() {
        return logisticsRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get logistics by ID
    public LogisticsDTO getLogisticsById(Long id) {
        return logisticsRepository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new EntityNotFoundException("Logistics", id));
    }

    // Update logistics
    public void updateLogistics(Long id, LogisticsDTO logisticsDTO) {
        Logistics logistics = logisticsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Logistics", id));

        if (logisticsDTO.getDonationId() != null) {
            Donation donation = donationRepository.findById(logisticsDTO.getDonationId())
                .orElseThrow(() -> new EntityNotFoundException("Donation", logisticsDTO.getDonationId()));
            logistics.setDonation(donation);
        }
        if (logisticsDTO.getPickupLocation() != null) {
            logistics.setPickupLocation(logisticsDTO.getPickupLocation());
        }
        if (logisticsDTO.getDeliveryLocation() != null) {
            logistics.setDeliveryLocation(logisticsDTO.getDeliveryLocation());
        }
        if (logisticsDTO.getStatus() != null) {
            logistics.setStatus(logisticsDTO.getStatus());
        }

        logisticsRepository.save(logistics);
    }

    // Delete logistics
    public void deleteLogistics(Long id) {
        if (!logisticsRepository.existsById(id)) {
            throw new EntityNotFoundException("Logistics", id);
        }
        logisticsRepository.deleteById(id);
    }

    // Check if exists
    public boolean existsById(Long id) {
        return logisticsRepository.existsById(id);
    }

    // Get logistics by donation ID
    public List<LogisticsDTO> getLogisticsByDonationId(Long donationId) {
        return logisticsRepository.findByDonation_DonationId(donationId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get logistics by status
    public List<LogisticsDTO> getLogisticsByStatus(String status) {
        return logisticsRepository.findByStatus(status).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get logistics by pickup location
    public List<LogisticsDTO> getLogisticsByPickupLocation(String location) {
        return logisticsRepository.findByPickupLocationContaining(location).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get logistics by delivery location
    public List<LogisticsDTO> getLogisticsByDeliveryLocation(String location) {
        return logisticsRepository.findByDeliveryLocationContaining(location).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Convert entity to DTO
    private LogisticsDTO convertToDTO(Logistics logistics) {
        LogisticsDTO dto = new LogisticsDTO();
        dto.setLogisticsId(logistics.getLogisticsId());
        dto.setDonationId(logistics.getDonation().getDonationId());
        dto.setPickupLocation(logistics.getPickupLocation());
        dto.setDeliveryLocation(logistics.getDeliveryLocation());
        dto.setStatus(logistics.getStatus());
        return dto;
    }
}