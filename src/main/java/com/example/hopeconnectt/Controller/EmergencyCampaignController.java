package com.example.hopeconnectt.Controller;
import com.example.hopeconnectt.Models.Entity.EmergencyCampaign;
import com.example.hopeconnectt.Services.EmergencyCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-campaigns")
public class EmergencyCampaignController {

    @Autowired
    private EmergencyCampaignService service;

    @GetMapping
    public List<EmergencyCampaign> getAllCampaigns() {
        return service.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public EmergencyCampaign getCampaignById(@PathVariable Long id) {
        return service.getCampaignById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    @PostMapping
    public EmergencyCampaign createCampaign(@RequestBody EmergencyCampaign campaign) {
        return service.createCampaign(campaign);
    }

    @PutMapping("/{id}")
    public EmergencyCampaign updateCampaign(@PathVariable Long id, @RequestBody EmergencyCampaign campaign) {
        return service.updateCampaign(id, campaign);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        service.deleteCampaign(id);
    }
}

