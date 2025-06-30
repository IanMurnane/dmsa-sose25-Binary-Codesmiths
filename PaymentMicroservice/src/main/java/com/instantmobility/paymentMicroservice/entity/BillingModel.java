package com.instantmobility.paymentMicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BillingModel {

    @Column(name = "billing_unit")
    private String unit;

    @Column(name = "billing_rate")
    private double rate;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
