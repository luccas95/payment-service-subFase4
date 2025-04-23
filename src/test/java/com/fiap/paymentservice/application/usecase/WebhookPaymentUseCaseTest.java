package com.fiap.paymentservice.application.usecase;

import com.fiap.paymentservice.domain.entity.Payment;
import com.fiap.paymentservice.domain.enums.PaymentStatus;
import com.fiap.paymentservice.infrastructure.controller.dto.PaymentWebhookRequest;
import com.fiap.paymentservice.infrastructure.gateway.PaymentGateway;
import com.fiap.paymentservice.infrastructure.gateway.SaleClientGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WebhookPaymentUseCaseTest {

    private PaymentGateway gateway;
    private SaleClientGateway saleClientGateway;
    private WebhookPaymentUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(PaymentGateway.class);
        saleClientGateway = mock(SaleClientGateway.class);
        useCase = new WebhookPaymentUseCase(gateway, saleClientGateway);
    }

    @Test
    void shouldUpdatePaymentStatusToConfirmed() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setSaleId(1L);
        payment.setAmount(100.0);
        payment.setStatus(PaymentStatus.PENDING);

        when(gateway.findById(1L)).thenReturn(payment);

        PaymentWebhookRequest request = new PaymentWebhookRequest();
        request.setPaymentId(1L);
        request.setStatus("APROVADO"); // Corrigido aqui!

        useCase.execute(request.getPaymentId(), request.getStatus());

        ArgumentCaptor<Payment> captor = ArgumentCaptor.forClass(Payment.class);
        verify(gateway).save(captor.capture());

        Payment saved = captor.getValue();
        assertEquals(PaymentStatus.CONFIRMED, saved.getStatus());
        verify(saleClientGateway).updateSaleStatus(1L, "concluir");
    }
}
