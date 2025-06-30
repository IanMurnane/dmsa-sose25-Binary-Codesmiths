package com.instantmobility.paymentMicroservice.controller;

import com.instantmobility.paymentMicroservice.dto.PaymentDTO;
import com.instantmobility.paymentMicroservice.dto.PaymentRequest;
import com.instantmobility.paymentMicroservice.entity.Payment;
import com.instantmobility.paymentMicroservice.service.PaymentProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentProcessingService paymentService;

    @PostMapping
    public PaymentDTO processPayment(@RequestBody PaymentRequest request) {
        Payment payment = paymentService.processPayment(request);
        return toDTO(payment);
    }

    @GetMapping("/{bookingId}")
    public PaymentDTO getPayment(@PathVariable String bookingId) {
        Payment payment = paymentService.getPaymentByBookingId(bookingId);
        return toDTO(payment);
    }

    @GetMapping
    public String index() {
        return "Payment service is running!";
    }

    private PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getBookingId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getBillingModel().getRate(),
                payment.getBillingModel().getUnit()
        );
    }
}
