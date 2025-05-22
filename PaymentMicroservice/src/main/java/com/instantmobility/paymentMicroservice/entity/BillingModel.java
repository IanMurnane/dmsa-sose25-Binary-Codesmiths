package com.instantmobility.paymentMicroservice.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class BillingModel {

    private String unit; // e.g., per_hour, per_km
    private Double rate;

    // Constructors
    public BillingModel() {}

    public BillingModel(String unit, Double rate) {
        this.unit = unit;
        this.rate = rate;
    }

    // Getters and Setters
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public Double getRate() { return rate; }
    public void setRate(Double rate) { this.rate = rate; }
}