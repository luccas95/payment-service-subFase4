
package com.fiap.paymentservice.infrastructure.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentWebhookRequestTest {

    @Test
    void shouldSetAndGetFields() {
        PaymentWebhookRequest webhook = new PaymentWebhookRequest();
        webhook.setPaymentId(1L);
        webhook.setStatus("CONCLUIDO");

        assertEquals(1L, webhook.getPaymentId());
        assertEquals("CONCLUIDO", webhook.getStatus());
    }
}
