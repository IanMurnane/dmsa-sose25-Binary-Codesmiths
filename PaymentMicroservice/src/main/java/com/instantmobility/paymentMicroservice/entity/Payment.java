package com.instantmobility.paymentMicroservice.entity;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    private String id;

    @Column(name = "booking_id")
    private String bookingId;

    private double amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Embedded
    private BillingModel billingModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public BillingModel getBillingModel() {
        return billingModel;
    }

    public void setBillingModel(BillingModel billingModel) {
        this.billingModel = billingModel;
    }
}
