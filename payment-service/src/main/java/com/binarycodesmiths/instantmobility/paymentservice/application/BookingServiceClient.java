package com.binarycodesmiths.instantmobility.paymentservice.application;

public class BookingServiceClient {
    public boolean validateBookingId(String bookingId) {
        if (bookingId == null) {
            return false;
        }
        
        return true;
    }
}