package com.mobility.ratingmicroservice.service.impl;

import com.mobility.ratingmicroservice.dto.RatingDTO;
import com.mobility.ratingmicroservice.entity.Rating;
import com.mobility.ratingmicroservice.repository.RatingRepository;
import com.mobility.ratingmicroservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(RatingDTO ratingDTO) {
        // Validate the rating value (1-5)
        validateRatingValue(ratingDTO.getRatingValue());
        
        Rating rating = Rating.builder()
                .userId(ratingDTO.getUserId())
                .vehicleId(ratingDTO.getVehicleId())
                .bookingId(ratingDTO.getBookingId())
                .providerId(ratingDTO.getProviderId())
                .ratingValue(ratingDTO.getRatingValue())
                .comments(ratingDTO.getComments())
                .build();
                
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating updateRating(Long id, RatingDTO ratingDTO) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        
        // Validate the rating value (1-5)
        validateRatingValue(ratingDTO.getRatingValue());
        
        existingRating.setUserId(ratingDTO.getUserId());
        existingRating.setVehicleId(ratingDTO.getVehicleId());
        existingRating.setBookingId(ratingDTO.getBookingId());
        existingRating.setProviderId(ratingDTO.getProviderId());
        existingRating.setRatingValue(ratingDTO.getRatingValue());
        existingRating.setComments(ratingDTO.getComments());
        
        return ratingRepository.save(existingRating);
    }

    @Override
    public void deleteRating(Long id) {
        if (!ratingRepository.existsById(id)) {
            throw new RuntimeException("Rating not found with id: " + id);
        }
        ratingRepository.deleteById(id);
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByVehicleId(String vehicleId) {
        return ratingRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<Rating> getRatingsByBookingId(String bookingId) {
        return ratingRepository.findByBookingId(bookingId);
    }

    @Override
    public List<Rating> getRatingsByProviderId(String providerId) {
        return ratingRepository.findByProviderId(providerId);
    }

    @Override
    public Double getAverageRatingByVehicleId(String vehicleId) {
        Double avgRating = ratingRepository.getAverageRatingByVehicleId(vehicleId);
        return avgRating != null ? avgRating : 0.0;
    }

    @Override
    public Double getAverageRatingByProviderId(String providerId) {
        Double avgRating = ratingRepository.getAverageRatingByProviderId(providerId);
        return avgRating != null ? avgRating : 0.0;
    }
    
    private void validateRatingValue(Integer ratingValue) {
        if (ratingValue == null || ratingValue < 1 || ratingValue > 5) {
            throw new IllegalArgumentException("Rating value must be between 1 and 5");
        }
    }
} 