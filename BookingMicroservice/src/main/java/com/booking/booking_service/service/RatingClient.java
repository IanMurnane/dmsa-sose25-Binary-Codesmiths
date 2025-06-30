package com.booking.booking_service.service;

import com.booking.booking_service.dto.RatingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingClient {

    @PostMapping("/ratings")
    ResponseEntity<?> createRating(@RequestBody RatingDTO ratingDTO);

    @GetMapping("/ratings")
    List<RatingDTO> getRatings();
}
