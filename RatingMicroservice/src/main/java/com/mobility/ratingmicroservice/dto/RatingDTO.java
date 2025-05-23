package com.mobility.ratingmicroservice.dto;

import lombok.Data;

@Data
public class RatingDTO {
    private String userId;
    private String vehicleId;
    private String bookingId;
    private String providerId;
    private Integer ratingValue;
    private String comments;
} 