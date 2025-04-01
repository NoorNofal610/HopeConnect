package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.DTO.LogisticsDTO;
import com.example.hopeconnectt.Services.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logistics")
public class LogisticsController {
    @Autowired
    private LogisticsService logisticsService;

    // Create logistics record
    @PostMapping
    public ResponseEntity<LogisticsDTO> createLogistics(@RequestBody LogisticsDTO logisticsDTO) {
        LogisticsDTO createdLogistics = logisticsService.createLogistics(logisticsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLogistics);
    }

    // Get all logistics records
    @GetMapping
    public ResponseEntity<List<LogisticsDTO>> getAllLogistics() {
        return ResponseEntity.ok(logisticsService.getAllLogistics());
    }

    // Get logistics by ID
    @GetMapping("/{id}")
    public ResponseEntity<LogisticsDTO> getLogisticsById(@PathVariable Long id) {
        return ResponseEntity.ok(logisticsService.getLogisticsById(id));
    }

    

    // Get logistics by donation ID
    @GetMapping("/donation/{donationId}")
    public ResponseEntity<List<LogisticsDTO>> getLogisticsByDonationId(@PathVariable Long donationId) {
        return ResponseEntity.ok(logisticsService.getLogisticsByDonationId(donationId));
    }

    // Get logistics by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LogisticsDTO>> getLogisticsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(logisticsService.getLogisticsByStatus(status));
    }

    // Get logistics by pickup location
    @GetMapping("/pickup-location")
    public ResponseEntity<List<LogisticsDTO>> getLogisticsByPickupLocation(
            @RequestParam String location) {
        return ResponseEntity.ok(logisticsService.getLogisticsByPickupLocation(location));
    }

    // Get logistics by delivery location
    @GetMapping("/delivery-location")
    public ResponseEntity<List<LogisticsDTO>> getLogisticsByDeliveryLocation(
            @RequestParam String location) {
        return ResponseEntity.ok(logisticsService.getLogisticsByDeliveryLocation(location));
    }

    ////////////////////////////
    /// // Update logistics
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLogistics(
            @PathVariable Long id,
            @RequestBody LogisticsDTO logisticsDTO) {
        try {
            logisticsService.updateLogistics(id, logisticsDTO);
            return ResponseEntity.ok("Logistics with ID " + id + " updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating logistics: " + e.getMessage());
        }
    }

    // Delete logistics
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLogistics(@PathVariable Long id) {
        try {
            if (logisticsService.existsById(id)) {
                logisticsService.deleteLogistics(id);
                return ResponseEntity.ok("Logistics with ID " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Logistics with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting logistics: " + e.getMessage());
        }
    }
}
