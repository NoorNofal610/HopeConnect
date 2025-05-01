package com.example.hopeconnectt.Services;

import com.example.hopeconnectt.Models.Enumes.ReviewStatus;
import com.example.hopeconnectt.Models.Entity.*;
import com.example.hopeconnectt.Reposotires.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final DonorRepository donorRepository;
    private final OrphanageRepository orphanageRepository;
    
    @Transactional
    public Review createReview(Long donorId, Long orphanageId, Integer rating, String comment) {
        Donor donor = donorRepository.findById(donorId)
            .orElseThrow(() -> new EntityNotFoundException("Donor not found with ID: " + donorId));
        
        Orphanage orphanage = orphanageRepository.findById(orphanageId)
            .orElseThrow(() -> new EntityNotFoundException("Orphanage not found with ID: " + orphanageId));
        
        // Validate rating
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    
        return reviewRepository.save(Review.builder()
            .donor(donor)
            .orphanage(orphanage)
            .rating(rating)
            .comment(comment)
            .date(LocalDate.now())
            .status(ReviewStatus.ACTIVE)
            .build());
    }
    
    @Transactional(readOnly = true)
    public List<Review> getReviewsByDonor(Long donorId) {
        if (!donorRepository.existsById(donorId)) {
            throw new EntityNotFoundException("Donor not found");
        }
        return reviewRepository.findByDonor_DonorId(donorId);
    }

    

    public List<Review> getReviewsByOrphanageAndRating(Long orphanageId, Integer rating) {
        try {
            log.info("Fetching reviews for orphanage {} with rating {}", orphanageId, rating);
            
            // Explicit validation
            if (rating == null || rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1-5");
            }

            return reviewRepository.findByOrphanageAndRating(orphanageId, rating);
        } catch (Exception e) {
            log.error("Error fetching reviews: {}", e.getMessage());
            throw e; // Re-throw for controller handling
        }
    }
    
    public List<Review> getActiveReviews() {
        return reviewRepository.findByStatus(ReviewStatus.ACTIVE);
    }
    
    @Transactional
    public Review updateReviewStatus(Long reviewId, ReviewStatus status) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new RuntimeException("Review not found"));
        
        review.setStatus(status);
        return reviewRepository.save(review);
    }
    
    public List<Review> getReviewsBetweenDates(LocalDate start, LocalDate end) {
        return reviewRepository.findByDateBetween(start, end);
    }
    
    public List<Review> getReviewsByMinRating(Integer minRating) {
        return reviewRepository.findByRatingGreaterThanEqual(minRating);
    }
    @Transactional(readOnly = true)
    public List<Review> getReviewsByOrphanage(Long orphanageId) {
        // Option 1: Strict check (returns 404 if orphanage doesn't exist)
        if (!orphanageRepository.existsById(orphanageId)) {
            throw new EntityNotFoundException("Orphanage not found with ID: " + orphanageId);
        }
        return reviewRepository.findByOrphanage_Id(orphanageId);
        
        // Option 2: Lenient approach (returns empty list if no reviews)
        // return reviewRepository.findByOrphanage_Id(orphanageId);
    }
    public Double getAverageRatingByOrphanageId(Long orphanageId) {
        if (!orphanageRepository.existsById(orphanageId)) {
            throw new EntityNotFoundException("Orphanage not found with ID: " + orphanageId);
        }
        return reviewRepository.findAverageRatingByOrphanageId(orphanageId);
    }
    @Transactional
public void deleteReview(Long reviewId) {
    Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new EntityNotFoundException("Review not found with ID: " + reviewId));
    
    log.info("Deleting review ID: {} (Rating: {}, Orphanage: {})", 
            reviewId, review.getRating(), review.getOrphanage().getId());
    
    reviewRepository.delete(review);
}
}