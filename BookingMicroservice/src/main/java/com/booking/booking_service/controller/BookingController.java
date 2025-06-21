package com.booking.booking_service.controller;

import com.booking.booking_service.dto.PaymentDTO;
import com.booking.booking_service.dto.PaymentRequest;
import com.booking.booking_service.dto.VehicleDTO;
import com.booking.booking_service.model.Booking;
import com.booking.booking_service.repository.BookingRepository;
import com.booking.booking_service.service.PaymentClient;
import com.booking.booking_service.service.VehicleClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Create Booking
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    // Get All Bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get Bookings by User ID
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    // Cancel Booking by ID
    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
    }

    // Get All Vehicles (proxy to VEHICLE-SERVICE)
    @GetMapping("/vehicles")
    public List<VehicleDTO> getVehicles() {
        return vehicleClient.getAllVehicles();
    }

    // Get Vehicle by ID (proxy to VEHICLE-SERVICE)
    @GetMapping("/vehicles/{id}")
    public VehicleDTO getVehicle(@PathVariable Long id) {
        return vehicleClient.getVehicleById(id);
    }

    // Process Payment (proxy to PAYMENT-SERVICE)
    @PostMapping("/payments")
    public PaymentDTO processPayment(@RequestBody PaymentRequest request) {
        return paymentClient.processPayment(request);
    }

    // Get Payment by ID (proxy to PAYMENT-SERVICE)
    @GetMapping("/payments/{id}")
    public PaymentDTO getPayment(@PathVariable Long id) {
        return paymentClient.getPayment(id);
    }
}
