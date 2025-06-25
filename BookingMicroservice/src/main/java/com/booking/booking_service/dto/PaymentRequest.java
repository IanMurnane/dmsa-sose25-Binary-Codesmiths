package com.booking.booking_service.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long bookingId;
    private Long userId;
    private Double amount;
    private String paymentMethod;
    private String billingUnit;
    private Double billingRate;
}
