package com.example.hopeconnectt.Reposotires;

import com.example.hopeconnectt.Models.Entity.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LogisticsRepository extends JpaRepository<Logistics, Long> {
    List<Logistics> findByDonation_DonationId(Long donationId);
    List<Logistics> findByStatus(String status);
    List<Logistics> findByPickupLocationContaining(String location);
    List<Logistics> findByDeliveryLocationContaining(String location);
}
