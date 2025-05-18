package com.booking.booking_service.service;

import com.booking.booking_service.model.booking;
import com.booking.booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public booking createBooking(booking booking) {
        return bookingRepository.save(booking);
    }

    public List<booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public booking updateBooking(booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
