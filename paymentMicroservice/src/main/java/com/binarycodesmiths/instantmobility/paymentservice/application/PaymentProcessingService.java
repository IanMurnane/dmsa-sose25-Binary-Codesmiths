package com.binarycodesmiths.instantmobility.paymentservice.application;

import com.binarycodesmiths.instantmobility.paymentservice.domain.Payment;
import com.binarycodesmiths.instantmobility.paymentservice.domain.BillingModel;
import com.binarycodesmiths.instantmobility.paymentservice.infrastructure.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentProcessingService {
    private final PaymentRepository paymentRepository;
    private final BookingServiceClient bookingServiceClient;

    public PaymentProcessingService(PaymentRepository paymentRepository, BookingServiceClient bookingServiceClient) {
        this.paymentRepository = paymentRepository;
        this.bookingServiceClient = bookingServiceClient;
    }

    public Payment processPayment(Long bookingId, Long userId, Double amount, String paymentMethod) {
        if (!bookingServiceClient.validateBookingId(bookingId)) {
            throw new IllegalArgumentException("Invalid booking ID");
        }

        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setBookingId(bookingId);
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentTime(LocalDateTime.now());

        BillingModel billingModel = new BillingModel();
        billingModel.setRate(10.0); // Mocked
        billingModel.setUnit("per_hour"); // Mocked
        payment.setBillingModel(billingModel);

        return paymentRepository.save(payment);
    }

    public Payment getPaymentByBookingId(String bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }
}