package com.booking.booking_service.service;

import com.booking.booking_service.dto.PaymentRequest;
import com.booking.booking_service.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {

    @PostMapping("/payments")
    PaymentDTO processPayment(@RequestBody PaymentRequest request);

    @GetMapping("/payments/{id}")
    PaymentDTO getPayment(@PathVariable Long id);
}
