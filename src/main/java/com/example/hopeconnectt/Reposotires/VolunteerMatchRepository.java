package com.example.hopeconnectt.Reposotires;



import com.example.hopeconnectt.Models.Entity.VolunteerMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VolunteerMatchRepository extends JpaRepository<VolunteerMatch, Long> {
    // Removed invalid field declaration

    List<VolunteerMatch> findByVolunteer_VolunteerId(Long volunteerId);
    List<VolunteerMatch> findByOrphanage_Id(Long orphanageId);
    List<VolunteerMatch> findByStatus(String status);
    List<VolunteerMatch> findByDateBetween(LocalDate start, LocalDate end);
    default List<VolunteerMatch> getAllMatches() {
        return findAll(); // Simple JPA query to fetch all matches
    }
    @Modifying
    @Query("DELETE FROM VolunteerMatch vm WHERE vm.matchId = :id")
    void deleteByMatchId(@Param("id") Long id);
}
