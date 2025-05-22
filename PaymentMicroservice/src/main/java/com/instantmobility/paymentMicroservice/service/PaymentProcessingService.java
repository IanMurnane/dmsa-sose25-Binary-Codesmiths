package com.instantmobility.paymentMicroservice.service;

import com.instantmobility.paymentMicroservice.entity.BillingModel;
import com.instantmobility.paymentMicroservice.entity.Payment;
import com.instantmobility.paymentMicroservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentProcessingService {

    private final PaymentRepository paymentRepository;

    public PaymentProcessingService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(Long bookingId, Long userId, Double amount, String paymentMethod, String billingUnit, Double billingRate) {
        // Mock validation: Assume bookingId and userId are valid for Task 1
        if (bookingId == null || userId == null || amount <= 0 || paymentMethod == null || billingUnit == null || billingRate <= 0) {
            throw new IllegalArgumentException("Invalid payment details");
        }

        BillingModel billingModel = new BillingModel(billingUnit, billingRate);
        Payment payment = new Payment(bookingId, userId, amount, billingModel, paymentMethod, LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with ID: " + id));
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}