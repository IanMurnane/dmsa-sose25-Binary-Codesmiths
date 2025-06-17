package com.booking.booking_service.controller;

import com.booking.booking_service.model.booking;
import com.booking.booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bookings")
public class bookingcontroller { // Class name should start with a capital letter

    @Autowired
    private BookingRepository bookingRepository;

    // Create Booking
    @PostMapping
    public booking createBooking(@RequestBody booking booking) {
        return bookingRepository.save(booking);
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
}
