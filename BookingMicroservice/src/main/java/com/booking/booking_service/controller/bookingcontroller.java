package com.booking.booking_service.controller;

import com.booking.booking_service.model.booking;
import com.booking.booking_service.repository.BookingRepository;
import com.booking.booking_service.service.ExternalServiceClient;
import com.booking.booking_service.service.bookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/bookings")
public class bookingcontroller { // Class name should start with a capital letter

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private bookingService bookingService;
    
    @Autowired
    private ExternalServiceClient externalServiceClient;

    // Create Booking with Circuit Breaker
    @PostMapping
    public booking createBooking(@RequestBody booking booking) {
        return bookingService.createBooking(booking);
    }

    // Get All Bookings
    @GetMapping
    public List<booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get Bookings by User ID
    @GetMapping("/user/{userId}")
    public List<booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    // Cancel Booking by ID
    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
    }
    
    // Demo endpoint to test vehicle service circuit breaker
    @GetMapping("/vehicle-info/{vehicleId}")
    public ResponseEntity<Map<String, Object>> getVehicleInfo(@PathVariable Long vehicleId) {
        try {
            CompletableFuture<Map<String, Object>> future = externalServiceClient.getVehicleDetails(vehicleId);
            return ResponseEntity.ok(future.get());
        } catch (InterruptedException | ExecutionException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to get vehicle information");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    // Demo endpoint to test payment service circuit breaker
    @PostMapping("/process-payment")
    public ResponseEntity<Map<String, Object>> processPayment(@RequestBody Map<String, Object> paymentDetails) {
        try {
            CompletableFuture<Map<String, Object>> future = externalServiceClient.processPayment(paymentDetails);
            return ResponseEntity.ok(future.get());
        } catch (InterruptedException | ExecutionException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to process payment");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
