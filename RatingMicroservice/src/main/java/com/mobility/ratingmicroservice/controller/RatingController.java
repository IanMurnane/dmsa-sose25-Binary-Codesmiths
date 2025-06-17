package com.mobility.ratingmicroservice.controller;

import com.mobility.ratingmicroservice.dto.RatingDTO;
import com.mobility.ratingmicroservice.entity.Rating;
import com.mobility.ratingmicroservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // Create a new rating
    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody RatingDTO ratingDTO) {
        try {
            Rating createdRating = ratingService.createRating(ratingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating rating: " + e.getMessage());
        }
    }

    // Get all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    // Get rating by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRatingById(@PathVariable Long id) {
        try {
            Rating rating = ratingService.getRatingById(id);
            return ResponseEntity.ok(rating);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Update rating
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRating(@PathVariable Long id, @RequestBody RatingDTO ratingDTO) {
        try {
            Rating updatedRating = ratingService.updateRating(id, ratingDTO);
            return ResponseEntity.ok(updatedRating);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating rating: " + e.getMessage());
        }
    }

    // Delete rating
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Long id) {
        try {
            ratingService.deleteRating(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Get ratings by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    // Get ratings by vehicle ID
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Rating>> getRatingsByVehicleId(@PathVariable String vehicleId) {
        return ResponseEntity.ok(ratingService.getRatingsByVehicleId(vehicleId));
    }

    // Get rating by booking ID
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<Rating>> getRatingsByBookingId(@PathVariable String bookingId) {
        return ResponseEntity.ok(ratingService.getRatingsByBookingId(bookingId));
    }

    // Get ratings by provider ID
    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Rating>> getRatingsByProviderId(@PathVariable String providerId) {
        return ResponseEntity.ok(ratingService.getRatingsByProviderId(providerId));
    }

    // Get average rating by vehicle ID
    @GetMapping("/vehicle/{vehicleId}/average")
    public ResponseEntity<Map<String, Double>> getAverageRatingByVehicleId(@PathVariable String vehicleId) {
        Double avgRating = ratingService.getAverageRatingByVehicleId(vehicleId);
        return ResponseEntity.ok(Map.of("averageRating", avgRating));
    }

    // Get average rating by provider ID
    @GetMapping("/provider/{providerId}/average")
    public ResponseEntity<Map<String, Double>> getAverageRatingByProviderId(@PathVariable String providerId) {
        Double avgRating = ratingService.getAverageRatingByProviderId(providerId);
        return ResponseEntity.ok(Map.of("averageRating", avgRating));
    }
} 