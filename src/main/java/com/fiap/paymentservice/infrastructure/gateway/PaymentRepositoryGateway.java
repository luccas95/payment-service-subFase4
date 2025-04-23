package com.fiap.paymentservice.infrastructure.gateway;

import com.fiap.paymentservice.domain.entity.Payment;
import com.fiap.paymentservice.infrastructure.repository.PaymentRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryGateway implements PaymentGateway {

    private final PaymentRepository repository;
    private final PaymentRepository paymentRepository;

    public PaymentRepositoryGateway(PaymentRepository repository, PaymentRepository paymentRepository) {
        this.repository = repository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com o ID informado"));
    }
}
