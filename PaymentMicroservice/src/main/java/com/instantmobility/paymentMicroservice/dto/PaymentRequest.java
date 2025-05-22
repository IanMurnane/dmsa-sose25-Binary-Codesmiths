package com.instantmobility.paymentMicroservice.dto;

public class PaymentRequest {

    private Long bookingId;
    private Long userId;
    private Double amount;
    private String paymentMethod;
    private String billingUnit;
    private Double billingRate;

    // Constructors
    public PaymentRequest() {}

    // Getters and Setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getBillingUnit() { return billingUnit; }
    public void setBillingUnit(String billingUnit) { this.billingUnit = billingUnit; }
    public Double getBillingRate() { return billingRate; }
    public void setBillingRate(Double billingRate) { this.billingRate = billingRate; }
}