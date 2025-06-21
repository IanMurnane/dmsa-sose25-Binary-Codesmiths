package com.booking.booking_service.controller;

import com.booking.booking_service.dto.PaymentDTO;
import com.booking.booking_service.dto.PaymentRequest;
import com.booking.booking_service.dto.RatingDTO;
import com.booking.booking_service.dto.VehicleDTO;
import com.booking.booking_service.model.Booking;
import com.booking.booking_service.repository.BookingRepository;
import com.booking.booking_service.service.PaymentClient;
import com.booking.booking_service.service.RatingClient;
import com.booking.booking_service.service.VehicleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleClient vehicleClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private RatingClient ratingClient;

    // Booking endpoints...
    @PostMapping("/bookings")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/bookings/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingRepository.findByUserId(userId);
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
}
