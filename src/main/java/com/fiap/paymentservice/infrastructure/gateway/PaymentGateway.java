package com.fiap.paymentservice.infrastructure.gateway;

import com.fiap.paymentservice.domain.entity.Payment;

public interface PaymentGateway {
    Payment save(Payment payment);
    Payment findById(Long id);
}
