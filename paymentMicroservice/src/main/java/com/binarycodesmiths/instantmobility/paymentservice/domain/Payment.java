package com.binarycodesmiths.instantmobility.paymentservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {
    @Id
    private String id;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "user_id")
    private Long userId;

    private Double amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_time")
    private LocalDateTime paymentTime;

    @Embedded
    private BillingModel billingModel;
}