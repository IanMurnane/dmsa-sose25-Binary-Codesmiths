package com.binarycodesmiths.instantmobility.paymentservice.infrastructure;

import com.binarycodesmiths.instantmobility.paymentservice.application.PaymentProcessingService;
import com.binarycodesmiths.instantmobility.paymentservice.domain.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentProcessingService paymentService;

    public PaymentController(PaymentProcessingService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> processPayment(@Valid @RequestBody PaymentRequest request) {
        try {
            Payment payment = paymentService.processPayment(
                request.getBookingId(),
                request.getUserId(),
                request.getAmount(),
                request.getPaymentMethod()
            );
            return ResponseEntity.status(201).body(payment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(null);
        }
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable String bookingId) {
        Payment payment = paymentService.getPaymentByBookingId(bookingId);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }
}

class PaymentRequest {
    @NotNull
    @JsonProperty("booking_id")
    private Long bookingId;

    @NotNul
    @JsonProperty("user_id")
    private Long userId;

    @NotNul
    private Double amount;

    @NotNul
    @JsonProperty("payment_method")
    private String paymentMethod;

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}