package com.binarycodesmiths.instantmobility.paymentservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    private String id;
    private String bookingId;
    private Double amount;
    private String paymentMethod;

    @Embedded
    private BillingModel billingModel;

    public void process() {
        // Placeholder for payment processing logic
    }
}