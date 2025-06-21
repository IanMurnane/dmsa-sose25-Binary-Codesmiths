package com.instantmobility.paymentMicroservice.controller;

import com.instantmobility.paymentMicroservice.dto.PaymentRequest;
import com.instantmobility.paymentMicroservice.entity.Payment;
import com.instantmobility.paymentMicroservice.service.PaymentProcessingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentProcessingService paymentService;

    public PaymentController(PaymentProcessingService paymentService) {
        this.paymentService = paymentService;
    }

    // REST API: Process a payment
    @PostMapping
    public Payment processPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(
                request.getBookingId(),
                request.getUserId(),
                request.getAmount(),
                request.getPaymentMethod(),
                request.getBillingUnit(),
                request.getBillingRate()
        );
    }

    // REST API: Retrieve payment details
    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }
}
