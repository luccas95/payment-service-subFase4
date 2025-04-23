
package com.fiap.paymentservice.infrastructure.controller.dto;

import com.fiap.paymentservice.domain.enums.PaymentStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentResponseTest {

    @Test
    void shouldSetAndGetFields() {
        PaymentResponse response = new PaymentResponse(1L, 2L, 1500.0, PaymentStatus.CONFIRMED);

        assertEquals(1L, response.getId());
        assertEquals(2L, response.getSaleId());
        assertEquals(1500.0, response.getAmount());
        assertEquals(PaymentStatus.CONFIRMED, response.getStatus());

        response.setId(10L);
        response.setSaleId(20L);
        response.setAmount(2000.0);
        response.setStatus(PaymentStatus.RECUSED);

        assertEquals(10L, response.getId());
        assertEquals(20L, response.getSaleId());
        assertEquals(2000.0, response.getAmount());
        assertEquals(PaymentStatus.RECUSED, response.getStatus());
    }
}
