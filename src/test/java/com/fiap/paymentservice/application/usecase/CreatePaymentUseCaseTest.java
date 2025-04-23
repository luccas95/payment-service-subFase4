package com.fiap.paymentservice.application.usecase;

import com.fiap.paymentservice.domain.entity.Payment;
import com.fiap.paymentservice.domain.enums.PaymentStatus;
import com.fiap.paymentservice.infrastructure.client.SaleClient;
import com.fiap.paymentservice.infrastructure.controller.dto.PaymentRequest;
import com.fiap.paymentservice.infrastructure.gateway.PaymentGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreatePaymentUseCaseTest {

    private PaymentGateway gateway;
    private SaleClient saleClient;
    private CreatePaymentUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(PaymentGateway.class);
        saleClient = mock(SaleClient.class);
        useCase = new CreatePaymentUseCase(gateway, saleClient);
    }

    @Test
    void shouldCreateAndSavePayment() {
        PaymentRequest request = new PaymentRequest();
        request.setSaleId(1L);
        request.setAmount(100.0);

        Payment pendingPayment = new Payment();
        pendingPayment.setId(1L);
        pendingPayment.setSaleId(1L);
        pendingPayment.setAmount(100.0);
        pendingPayment.setStatus(PaymentStatus.PENDING);

        Payment confirmedPayment = new Payment();
        confirmedPayment.setId(1L);
        confirmedPayment.setSaleId(1L);
        confirmedPayment.setAmount(100.0);
        confirmedPayment.setStatus(PaymentStatus.CONFIRMED);

        when(gateway.save(any(Payment.class)))
                .thenReturn(pendingPayment)   // primeira chamada
                .thenReturn(confirmedPayment); // segunda chamada (status atualizado)

        Payment result = useCase.execute(request);

        assertNotNull(result);
        assertEquals(1L, result.getSaleId());
        assertEquals(100.0, result.getAmount());
        assertEquals(PaymentStatus.CONFIRMED, result.getStatus());

        verify(gateway, times(2)).save(any(Payment.class));
    }
}
