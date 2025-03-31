package com.example.hopeconnectt.Reposotires;

import com.example.hopeconnectt.Models.Entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonor_DonorId(Long donorId);
    List<Donation> findByOrphanage_Id(Long orphanageId);
    List<Donation> findByCategory(String category);
    List<Donation> findByStatus(String status);
    List<Donation> findByAmountBetween(Double minAmount, Double maxAmount);
    List<Donation> findByAmountGreaterThanEqual(Double minAmount);
    List<Donation> findByAmountLessThanEqual(Double maxAmount);
    List<Donation> findByDateBetween(LocalDate startDate, LocalDate endDate);
}