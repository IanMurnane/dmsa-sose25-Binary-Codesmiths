package com.instantmobility.paymentMicroservice.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class BillingModel {

    private double rate;
    private String unit;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
