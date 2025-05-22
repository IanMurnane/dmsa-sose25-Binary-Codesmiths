package com.instantmobility.paymentMicroservice.repository;

import com.instantmobility.paymentMicroservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}