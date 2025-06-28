package com.booking.booking_service.controller;

import com.booking.booking_service.dto.*;
import com.booking.booking_service.entity.User;
import com.booking.booking_service.model.Booking;
import com.booking.booking_service.repository.BookingRepository;
import com.booking.booking_service.service.bookingService;
import com.booking.booking_service.service.PaymentClient;
import com.booking.booking_service.service.RatingClient;
import com.booking.booking_service.service.UserClient;
import com.booking.booking_service.service.VehicleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private bookingService bookingService;

    @Autowired
    private VehicleClient vehicleClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private RatingClient ratingClient;

    @Autowired
    private UserClient userClient;

    // Booking endpoints...
    @PostMapping("/bookings")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return bookingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/bookings/{id}")
    public void cancelBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
    }

    // Vehicle proxy
    @GetMapping("/vehicles")
    public List<VehicleDTO> getVehicles() {
        return vehicleClient.getAllVehicles();
    }

    @GetMapping("/vehicles/{id}")
    public VehicleDTO getVehicle(@PathVariable Long id) {
        return vehicleClient.getVehicleById(id);
    }

    // Payment proxy
    @PostMapping("/payments")
    public PaymentDTO processPayment(@RequestBody PaymentRequest request) {
        return paymentClient.processPayment(request);
    }

    @GetMapping("/payments/{id}")
    public PaymentDTO getPayment(@PathVariable Long id) {
        return paymentClient.getPayment(id);
    }

    // Rating proxy
    @PostMapping("/ratings")
    public ResponseEntity<?> submitRating(@RequestBody RatingDTO ratingDTO) {
        return ratingClient.createRating(ratingDTO);
    }

    // Auth Proxy
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return userClient.login(request);
    }

    // User Proxy
    @PostMapping("/users/register")
    public ResponseEntity<Map<String, Long>> registerUser(@RequestBody UserDTO userDTO) {
        return userClient.registerUser(userDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userClient.getUser(id);
    }
}
