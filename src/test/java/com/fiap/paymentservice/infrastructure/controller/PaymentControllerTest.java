package com.fiap.paymentservice.infrastructure.controller;

import com.fiap.paymentservice.application.usecase.CreatePaymentUseCase;
import com.fiap.paymentservice.application.usecase.WebhookPaymentUseCase;
import com.fiap.paymentservice.domain.entity.Payment;
import com.fiap.paymentservice.domain.enums.PaymentStatus;
import com.fiap.paymentservice.infrastructure.controller.dto.PaymentRequest;
import com.fiap.paymentservice.infrastructure.controller.dto.PaymentWebhookRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class PaymentControllerTest {

    private PaymentController controller;
    private CreatePaymentUseCase createUseCase;
    private WebhookPaymentUseCase webhookUseCase;

    @BeforeEach
    void setUp() {
        createUseCase = mock(CreatePaymentUseCase.class);
        webhookUseCase = mock(WebhookPaymentUseCase.class);
        controller = new PaymentController(createUseCase, webhookUseCase);
    }

    @Test
    void shouldCreatePayment() {
        PaymentRequest request = new PaymentRequest();
        request.setSaleId(1L);
        request.setAmount(200.0);

        Payment mockPayment = new Payment();
        mockPayment.setId(1L);
        mockPayment.setSaleId(1L);
        mockPayment.setAmount(200.0);
        mockPayment.setStatus(PaymentStatus.CONFIRMED);

        when(createUseCase.execute(any())).thenReturn(mockPayment);

        ResponseEntity<?> response = controller.create(request);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldSimulateWebhook() {
        PaymentWebhookRequest request = new PaymentWebhookRequest();
        request.setPaymentId(1L);
        request.setStatus("CONFIRMED");

        doNothing().when(webhookUseCase).execute(anyLong(), anyString());

        ResponseEntity<String> response = controller.simulateWebhook(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pagamento confirmado com sucesso", response.getBody());
        verify(webhookUseCase).execute(1L, "CONFIRMED");
    }
}
