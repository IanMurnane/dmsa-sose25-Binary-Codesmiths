package com.instantmobility.paymentMicroservice.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class BillingModel {

    @Column(name = "billing_rate")
    private double rate;

    @Column(name = "billing_unit")
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
