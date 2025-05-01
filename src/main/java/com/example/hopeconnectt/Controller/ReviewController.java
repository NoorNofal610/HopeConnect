package com.example.hopeconnectt.Controller;

import com.example.hopeconnectt.DTO.ReviewDTO;
import com.example.hopeconnectt.Models.Enumes.ReviewStatus;
import com.example.hopeconnectt.Models.Entity.Review;
import com.example.hopeconnectt.Services.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    
    private final ReviewService reviewService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createReview(
            @Valid @RequestBody ReviewDTO request) {
        try {
            Review review = reviewService.createReview(
                request.getDonorId(),
                request.getOrphanageId(),
                request.getRating(),
                request.getComment()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                    "status", "success",
                    "reviewId", review.getReviewId(),
                    "message", "Review created successfully"
                ));
        } catch (Exception e) {
            log.error("Error creating review: {}", e.getMessage(), e); // Log the full error
            return ResponseEntity.internalServerError().body(
                Map.of(
                    "status", "error",
                    "message", e.getMessage()  // Return the actual error message
                ));
        }
    }
    
    @GetMapping("/donor/{donorId}")
    public ResponseEntity<?> getByDonor(@PathVariable Long donorId) {
        try {
            List<Review> reviews = reviewService.getReviewsByDonor(donorId);
            return ResponseEntity.ok(reviews);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", e.getMessage())
            );
        } catch (Exception e) {
            log.error("Error fetching reviews for donor {}", donorId, e);
            return ResponseEntity.internalServerError().body(
                Map.of("error", "Failed to fetch reviews: " + e.getMessage())
            );
        }
    }
    @GetMapping("/orphanage/{orphanageId}/average")
public ResponseEntity<?> getAverageRating(
        @PathVariable Long orphanageId) {
    try {
        Double averageRating = reviewService.getAverageRatingByOrphanageId(orphanageId);
        return ResponseEntity.ok(Map.of(
            "orphanageId", orphanageId,
            "averageRating", averageRating != null ? averageRating : 0
        ));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
    }
}
@GetMapping("/orphanage/{orphanageId}/rating/{rating}")
public ResponseEntity<?> getByOrphanageAndRating(
        @PathVariable Long orphanageId,
        @PathVariable Integer rating) {
    
    try {
        List<Review> reviews = reviewService.getReviewsByOrphanageAndRating(orphanageId, rating);
        return ResponseEntity.ok(reviews);
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
                .body(Map.of("error", "Server error: " + e.getMessage()));
    }
}
    
    @GetMapping("/active")
    public ResponseEntity<?> getActiveReviews() {
        try {
            return ResponseEntity.ok(reviewService.getActiveReviews());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                Map.of("error", "Failed to fetch active reviews")
            );
        }
    }
    
    @PatchMapping("/{reviewId}/status")
    public ResponseEntity<Review> updateStatus(
            @PathVariable Long reviewId,
            @RequestParam ReviewStatus status) {
        return ResponseEntity.ok(reviewService.updateReviewStatus(reviewId, status));
    }
    
    @GetMapping("/between-dates")
    public ResponseEntity<?> getReviewsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        try {
            if (start.isAfter(end)) {
                return ResponseEntity.badRequest().body(
                    Map.of("error", "Start date must be before end date")
                );
            }
            return ResponseEntity.ok(reviewService.getReviewsBetweenDates(start, end));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                Map.of("error", "Failed to fetch reviews by date range")
            );
        }
    }
    
    @GetMapping("/rating/{minRating}")
    public ResponseEntity<List<Review>> getReviewsByMinRating(
            @PathVariable @Min(1) @Max(5) Integer minRating) {
        return ResponseEntity.ok(reviewService.getReviewsByMinRating(minRating));
    }
    @GetMapping("/orphanage/{orphanageId}")
    public ResponseEntity<?> getByOrphanage(@PathVariable Long orphanageId) {
        try {
            List<Review> reviews = reviewService.getReviewsByOrphanage(orphanageId);
            return ResponseEntity.ok(reviews);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
   @DeleteMapping("/{reviewId}")
public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
    try {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Review deleted successfully",
            "reviewId", reviewId,
            "timestamp", LocalDateTime.now()
        ));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
    } catch (Exception e) {
        log.error("Failed to delete review {}: {}", reviewId, e.getMessage());
        return ResponseEntity.internalServerError()
                .body(Map.of("error", "Failed to delete review: " + e.getMessage()));
    }
}
}