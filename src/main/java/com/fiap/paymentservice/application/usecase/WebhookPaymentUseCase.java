package com.fiap.paymentservice.application.usecase;

import com.fiap.paymentservice.domain.enums.PaymentStatus;
import com.fiap.paymentservice.domain.entity.Payment;
import com.fiap.paymentservice.infrastructure.gateway.PaymentGateway;
import com.fiap.paymentservice.infrastructure.gateway.SaleClientGateway;
import org.springframework.stereotype.Service;

@Service
public class WebhookPaymentUseCase {

    private final PaymentGateway paymentGateway;
    private final SaleClientGateway saleClientGateway;


    public WebhookPaymentUseCase(PaymentGateway paymentGateway, SaleClientGateway saleClientGateway) {
        this.paymentGateway = paymentGateway;
        this.saleClientGateway = saleClientGateway;
    }

    public void execute(Long paymentId, String status) {
        Payment payment = paymentGateway.findById(paymentId);

        if ("APROVADO".equalsIgnoreCase(status)) {
            payment.setStatus(PaymentStatus.CONFIRMED);
            saleClientGateway.updateSaleStatus(payment.getSaleId(), "concluir");
        } else if ("RECUSADO".equalsIgnoreCase(status)) {
            payment.setStatus(PaymentStatus.RECUSED);
            saleClientGateway.updateSaleStatus(payment.getSaleId(), "cancelar");
        }

        paymentGateway.save(payment);


    }
}
