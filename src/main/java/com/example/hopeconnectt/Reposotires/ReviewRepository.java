package com.example.hopeconnectt.Reposotires;

import com.example.hopeconnectt.Models.Entity.Review;
import com.example.hopeconnectt.Models.Enumes.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDonor_DonorId(Long donorId);
    List<Review> findByOrphanage_Id(Long orphanageId);
    List<Review> findByStatus(ReviewStatus status);
    List<Review> findByDateBetween(LocalDate start, LocalDate end);
    List<Review> findByRatingGreaterThanEqual(Integer minRating);
     @Query("SELECT AVG(r.rating) FROM Review r WHERE r.orphanage.id = :orphanageId")
    Double findAverageRatingByOrphanageId(@Param("orphanageId") Long orphanageId);
    @Query("SELECT r FROM Review r WHERE r.orphanage.id = :orphanageId AND r.rating = :rating")
    List<Review> findByOrphanageAndRating(
        @Param("orphanageId") Long orphanageId,
        @Param("rating") Integer rating);
        // Optional - if you want to return count of deleted rows
    @Modifying
    @Query("DELETE FROM Review r WHERE r.reviewId = :id")
    int deleteByReviewId(@Param("id") Long id);
    
}