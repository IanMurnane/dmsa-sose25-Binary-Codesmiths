package com.instantmobility.paymentMicroservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_db")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;
    private Long userId;
    private Double amount;

    @Embedded
    private BillingModel billingModel;

    private String paymentMethod;
    private LocalDateTime paymentTime;

    // Constructors
    public Payment() {}

    public Payment(Long bookingId, Long userId, Double amount, BillingModel billingModel, String paymentMethod, LocalDateTime paymentTime) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
        this.billingModel = billingModel;
        this.paymentMethod = paymentMethod;
        this.paymentTime = paymentTime;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public BillingModel getBillingModel() { return billingModel; }
    public void setBillingModel(BillingModel billingModel) { this.billingModel = billingModel; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public LocalDateTime getPaymentTime() { return paymentTime; }
    public void setPaymentTime(LocalDateTime paymentTime) { this.paymentTime = paymentTime; }
}