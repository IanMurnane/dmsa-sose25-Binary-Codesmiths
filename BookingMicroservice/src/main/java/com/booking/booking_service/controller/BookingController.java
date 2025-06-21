package com.booking.booking_service.controller;

import com.booking.booking_service.dto.VehicleDTO;
import com.booking.booking_service.model.Booking;
import com.booking.booking_service.repository.BookingRepository;
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

    // 🚗 New: Proxy call to get all vehicles
    @GetMapping("/vehicles")
    public List<VehicleDTO> getVehicles() {
        return vehicleClient.getAllVehicles();
    }

    // 🚗 New: Proxy call to get a single vehicle
    @GetMapping("/vehicles/{id}")
    public VehicleDTO getVehicle(@PathVariable Long id) {
        return vehicleClient.getVehicleById(id);
    }
}
