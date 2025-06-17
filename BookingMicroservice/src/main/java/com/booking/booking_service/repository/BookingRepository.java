package com.booking.booking_service.repository;

import com.booking.booking_service.model.booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<booking, Long> {
    // Custom method to find bookings by user ID
    List<booking> findByUserId(Long userId);
}
