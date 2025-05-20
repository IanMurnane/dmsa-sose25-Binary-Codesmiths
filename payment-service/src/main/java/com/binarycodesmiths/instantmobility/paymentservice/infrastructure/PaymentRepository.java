package com.binarycodesmiths.instantmobility.paymentservice.infrastructure;

import com.binarycodesmiths.instantmobility.paymentservice.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    Payment findByBookingId(String bookingId);
}