
package com.fiap.paymentservice.infrastructure.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRequestTest {

    @Test
    void shouldSetAndGetFields() {
        PaymentRequest request = new PaymentRequest();
        request.setSaleId(1L);
        request.setAmount(1500.0);

        assertEquals(1L, request.getSaleId());
        assertEquals(1500.0, request.getAmount());
    }
}
