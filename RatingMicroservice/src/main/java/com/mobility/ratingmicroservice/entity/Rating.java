package com.mobility.ratingmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String vehicleId;
    
    @Column(nullable = false)
    private String bookingId;
    
    @Column(nullable = false)
    private String providerId;
    
    @Column(nullable = false)
    private Integer ratingValue;
    
    @Column
    private String comments;
} 