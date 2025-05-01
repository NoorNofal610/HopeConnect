package com.example.hopeconnectt.Reposotires;


import com.example.hopeconnectt.Models.Entity.Sponsorship;
import com.example.hopeconnectt.Models.Enumes.SponsorshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface SponsorshipRepository extends JpaRepository<Sponsorship, Long> {
    List<Sponsorship> findByOrphan_Id(Long orphanId);  // Correct orphan reference
    List<Sponsorship> findByDonor_DonorId(Long donorId);  // Correct donor reference
    List<Sponsorship> findByStatus(SponsorshipStatus status);
    List<Sponsorship> findByStartDateBetween(LocalDate start, LocalDate end);
    @Modifying
    @Query("DELETE FROM Sponsorship s WHERE s.sponsorshipId = :sponsorshipId")
    void deleteBySponsorshipId(@Param("sponsorshipId") Long sponsorshipId);
}
