package com.mobility.ratingmicroservice.repository;

import com.mobility.ratingmicroservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    
    List<Rating> findByUserId(String userId);
    
    List<Rating> findByVehicleId(String vehicleId);
    
    List<Rating> findByBookingId(String bookingId);
    
    List<Rating> findByProviderId(String providerId);
    
    @Query("SELECT AVG(r.ratingValue) FROM Rating r WHERE r.vehicleId = :vehicleId")
    Double getAverageRatingByVehicleId(@Param("vehicleId") String vehicleId);
    
    @Query("SELECT AVG(r.ratingValue) FROM Rating r WHERE r.providerId = :providerId")
    Double getAverageRatingByProviderId(@Param("providerId") String providerId);
} 