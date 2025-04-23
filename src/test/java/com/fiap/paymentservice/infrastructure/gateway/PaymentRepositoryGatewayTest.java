package com.fiap.paymentservice.infrastructure.gateway;

import com.fiap.paymentservice.domain.entity.Payment;
import com.fiap.paymentservice.domain.enums.PaymentStatus;
import com.fiap.paymentservice.infrastructure.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentRepositoryGatewayTest {

    private PaymentRepository repository;
    private PaymentRepository paymentRepository;
    private PaymentRepositoryGateway gateway;

    @BeforeEach
    void setUp() {
        repository = mock(PaymentRepository.class);
        paymentRepository = mock(PaymentRepository.class);
        gateway = new PaymentRepositoryGateway(repository, paymentRepository);
    }

    @Test
    void shouldSavePayment() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setSaleId(1L);
        payment.setAmount(100.0);
        payment.setStatus(PaymentStatus.CONFIRMED);

        when(repository.save(payment)).thenReturn(payment);

        Payment result = gateway.save(payment);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).save(payment);
    }

    @Test
    void shouldFindPaymentById() {
        Payment payment = new Payment();
        payment.setId(1L);

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        Payment result = gateway.findById(1L);

        assertEquals(1L, result.getId());
        verify(paymentRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionIfPaymentNotFound() {
        when(paymentRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gateway.findById(99L);
        });

        assertEquals("Pagamento não encontrado com o ID informado", exception.getMessage());
        verify(paymentRepository).findById(99L);
    }
}
