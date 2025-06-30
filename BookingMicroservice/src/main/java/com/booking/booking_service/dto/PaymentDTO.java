package com.booking.booking_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long id;
    private String bookingId;
    private String userId;
    private Double amount;
    private String paymentMethod;
    private String billingUnit;
    private Double billingRate;
    private String status;
    private LocalDateTime timestamp;
}
