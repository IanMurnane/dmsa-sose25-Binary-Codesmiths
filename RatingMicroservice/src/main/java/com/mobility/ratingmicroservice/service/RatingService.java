package com.mobility.ratingmicroservice.service;

import com.mobility.ratingmicroservice.dto.RatingDTO;
import com.mobility.ratingmicroservice.entity.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(RatingDTO ratingDTO);
    Rating getRatingById(Long id);
    List<Rating> getAllRatings();
    Rating updateRating(Long id, RatingDTO ratingDTO);
    void deleteRating(Long id);
    List<Rating> getRatingsByUserId(String userId);
    List<Rating> getRatingsByVehicleId(String vehicleId);
    List<Rating> getRatingsByBookingId(String bookingId);
    List<Rating> getRatingsByProviderId(String providerId);
    Double getAverageRatingByVehicleId(String vehicleId);
    Double getAverageRatingByProviderId(String providerId);
} 