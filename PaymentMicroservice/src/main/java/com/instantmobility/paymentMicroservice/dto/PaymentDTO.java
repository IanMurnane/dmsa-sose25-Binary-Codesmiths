package com.instantmobility.paymentMicroservice.dto;

import java.time.LocalDateTime;

public class PaymentDTO {
    private Long id;
    private String bookingId;
    private double amount;
    private String paymentMethod;
    private double billingRate;
    private String billingUnit;
    private String status;
    private LocalDateTime timestamp;
    private String userId;

    public PaymentDTO() {}

    public PaymentDTO(Long id, String bookingId, double amount,
                      String paymentMethod, double billingRate, String billingUnit,
                      String status, LocalDateTime timestamp, String userId) {
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.billingRate = billingRate;
        this.billingUnit = billingUnit;
        this.status = status;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getBillingRate() {
        return billingRate;
    }

    public void setBillingRate(double billingRate) {
        this.billingRate = billingRate;
    }

    public String getBillingUnit() {
        return billingUnit;
    }

    public void setBillingUnit(String billingUnit) {
        this.billingUnit = billingUnit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
