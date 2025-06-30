package com.instantmobility.paymentMicroservice.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Payment {

    @Id
    private UUID id;

    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "user_id")
    private int userId;

    private double amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Embedded
    private BillingModel billingModel;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public BillingModel getBillingModel() {
        return billingModel;
    }

    public void setBillingModel(BillingModel billingModel) {
        this.billingModel = billingModel;
    }
}
