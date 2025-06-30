package com.instantmobility.paymentMicroservice.controller;

import com.instantmobility.paymentMicroservice.dto.PaymentRequest;
import com.instantmobility.paymentMicroservice.dto.PaymentDTO;
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
    public PaymentDTO getPaymentByBookingId(@PathVariable int bookingId) {
        Payment payment = paymentService.getPaymentByBookingId(bookingId);
        return toDTO(payment);
    }

    private PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId().toString(),                          // String
                payment.getBookingId(),                              // int
                payment.getUserId(),                                 // int
                payment.getAmount(),                                 // double
                payment.getPaymentMethod(),                          // String
                payment.getBillingModel().getUnit(),                 // String
                payment.getBillingModel().getRate()                  // double
        );
    }
}
