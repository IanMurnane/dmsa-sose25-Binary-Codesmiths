package com.binarycodesmiths.instantmobility.paymentservice.application;

import org.springframework.stereotype.Component;

@Component
public class MockBookingServiceClient implements BookingServiceClient {
    @Override
    public boolean validateBookingId(String bookingId) {
        return true; // Mocked for Task 1
    }
}