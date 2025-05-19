package com.binarycodesmiths.instantmobility.paymentservice.application;

import com.binarycodesmiths.instantmobility.paymentservice.domain.Payment;
import com.binarycodesmiths.instantmobility.paymentservice.domain.BillingModel;
import com.binarycodesmiths.instantmobility.paymentservice.infrastructure.PaymentRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PaymentProcessingService {
    private final PaymentRepository paymentRepository;

    public PaymentProcessingService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(String bookingId, Double amount, String paymentMethod) {
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setBookingId(bookingId);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);

        BillingModel billingModel = new BillingModel();
        billingModel.setRate(10.0); // Mocked
        billingModel.setUnit("per_hour"); // Mocked
        payment.setBillingModel(billingModel);

        payment.process();
        return paymentRepository.save(payment);
    }
}