package com.booking.booking_service.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ExternalServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(ExternalServiceClient.class);
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String VEHICLE_SERVICE_URL = "http://vehicle-service/api/vehicles";
    private static final String PAYMENT_SERVICE_URL = "http://payment-service/api/payments";
    
    // Circuit Breaker pattern for vehicle service
    @CircuitBreaker(name = "vehicleService", fallbackMethod = "getVehicleDetailsFallback")
    @TimeLimiter(name = "vehicleService")
    @Retry(name = "vehicleService")
    public CompletableFuture<Map<String, Object>> getVehicleDetails(Long vehicleId) {
        logger.info("Calling Vehicle Service for vehicle ID: {}", vehicleId);
        String url = VEHICLE_SERVICE_URL + "/" + vehicleId;
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url, Map.class));
    }
    
    // Fallback method for vehicle service
    public CompletableFuture<Map<String, Object>> getVehicleDetailsFallback(Long vehicleId, Exception ex) {
        logger.error("Circuit breaker triggered for Vehicle Service. Error: {}", ex.getMessage());
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("vehicleId", vehicleId);
        fallbackResponse.put("status", "UNAVAILABLE");
        fallbackResponse.put("message", "Vehicle service is currently unavailable. Please try again later.");
        return CompletableFuture.completedFuture(fallbackResponse);
    }
    
    // Circuit Breaker pattern for payment service
    @CircuitBreaker(name = "paymentService", fallbackMethod = "processPaymentFallback")
    @TimeLimiter(name = "paymentService")
    @Retry(name = "paymentService")
    public CompletableFuture<Map<String, Object>> processPayment(Map<String, Object> paymentDetails) {
        logger.info("Calling Payment Service for booking: {}", paymentDetails.get("bookingId"));
        return CompletableFuture.supplyAsync(() -> restTemplate.postForObject(PAYMENT_SERVICE_URL, paymentDetails, Map.class));
    }
    
    // Fallback method for payment service
    public CompletableFuture<Map<String, Object>> processPaymentFallback(Map<String, Object> paymentDetails, Exception ex) {
        logger.error("Circuit breaker triggered for Payment Service. Error: {}", ex.getMessage());
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("bookingId", paymentDetails.get("bookingId"));
        fallbackResponse.put("status", "PENDING");
        fallbackResponse.put("message", "Payment service is currently unavailable. Your payment will be processed later.");
        return CompletableFuture.completedFuture(fallbackResponse);
    }
} 