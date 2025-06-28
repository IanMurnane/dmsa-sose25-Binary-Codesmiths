package com.booking.booking_service.service;

import com.booking.booking_service.model.Booking;
import com.booking.booking_service.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class bookingService {
    
    private static final Logger logger = LoggerFactory.getLogger(bookingService.class);

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private ExternalServiceClient externalServiceClient;
    
    @Autowired
    private VehicleClient vehicleClient;
    
    @Autowired
    private PaymentClient paymentClient;

    public Booking createBooking(Booking booking) {
        // Save the booking first
        Booking savedBooking = bookingRepository.save(booking);
        
        try {
            // Get vehicle details using circuit breaker
            CompletableFuture<Map<String, Object>> vehicleDetailsFuture = 
                    externalServiceClient.getVehicleDetails(booking.getVehicleId());
            
            // Process payment using circuit breaker
            Map<String, Object> paymentDetails = new HashMap<>();
            paymentDetails.put("bookingId", savedBooking.getId());
            paymentDetails.put("userId", savedBooking.getUserId());
            paymentDetails.put("amount", savedBooking.getAmount());
            CompletableFuture<Map<String, Object>> paymentFuture = 
                    externalServiceClient.processPayment(paymentDetails);
            
            // Wait for both futures to complete
            CompletableFuture.allOf(vehicleDetailsFuture, paymentFuture).join();
            
            // Get the results
            Map<String, Object> vehicleDetails = vehicleDetailsFuture.get();
            Map<String, Object> paymentResult = paymentFuture.get();
            
            // Update booking with payment status
            if (paymentResult.containsKey("status")) {
                savedBooking.setPaymentStatus(paymentResult.get("status").toString());
                savedBooking = bookingRepository.save(savedBooking);
            }
            
            logger.info("Booking created successfully with ID: {}", savedBooking.getId());
            
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error during booking creation: {}", e.getMessage());
            // The circuit breaker fallback methods will handle failures
        }
        
        return savedBooking;
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
