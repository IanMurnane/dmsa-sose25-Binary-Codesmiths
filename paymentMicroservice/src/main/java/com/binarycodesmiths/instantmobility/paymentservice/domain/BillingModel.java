package com.binarycodesmiths.instantmobility.paymentservice.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class BillingModel {
    private Double rate;
    private String unit; // e.g., "per_hour", "per_km"
}