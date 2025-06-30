package com.booking.booking_service.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String bookingId;
    private String userId;
    private Double amount;
    private String paymentMethod;
    private String billingUnit;
    private Double billingRate;
}
