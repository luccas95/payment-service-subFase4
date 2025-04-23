package com.fiap.paymentservice.infrastructure.controller;

import com.fiap.paymentservice.application.usecase.CreatePaymentUseCase;
import com.fiap.paymentservice.application.usecase.WebhookPaymentUseCase;
import com.fiap.paymentservice.domain.entity.Payment;
import com.fiap.paymentservice.infrastructure.controller.dto.PaymentRequest;
import com.fiap.paymentservice.infrastructure.controller.dto.PaymentResponse;
import com.fiap.paymentservice.infrastructure.controller.dto.PaymentWebhookRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final WebhookPaymentUseCase webhookPaymentUseCase;

    public PaymentController(CreatePaymentUseCase createPaymentUseCase, WebhookPaymentUseCase webhookPaymentUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.webhookPaymentUseCase = webhookPaymentUseCase;
    }

    @PostMapping(value = "/payments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PaymentResponse> create(@RequestBody PaymentRequest request) {
        Payment payment = createPaymentUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(payment));
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> simulateWebhook(@RequestBody PaymentWebhookRequest request) {
        webhookPaymentUseCase.execute(request.getPaymentId(), request.getStatus());

        return ResponseEntity.ok("Pagamento confirmado com sucesso");
    }

    private PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getSaleId(), payment.getAmount(), payment.getStatus());
    }
}
