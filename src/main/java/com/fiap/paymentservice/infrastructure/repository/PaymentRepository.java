package com.fiap.paymentservice.infrastructure.repository;

import com.fiap.paymentservice.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
