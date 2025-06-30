package com.instantmobility.paymentMicroservice.service;

import com.instantmobility.paymentMicroservice.dto.PaymentRequest;
import com.instantmobility.paymentMicroservice.entity.BillingModel;
import com.instantmobility.paymentMicroservice.entity.Payment;
import com.instantmobility.paymentMicroservice.exception.PaymentNotFoundException;
import com.instantmobility.paymentMicroservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentProcessingService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setBookingId(request.getBookingId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());

        BillingModel model = new BillingModel();
        model.setRate(request.getBillingRate());
        model.setUnit(request.getBillingUnit());

        payment.setBillingModel(model);
        return paymentRepository.save(payment);
    }

    public Payment getPaymentByBookingId(String bookingId) {
        Optional<Payment> payment = paymentRepository.findByBookingId(bookingId);
        return payment.orElseThrow(() -> new PaymentNotFoundException("Payment not found for bookingId: " + bookingId));
    }
}
