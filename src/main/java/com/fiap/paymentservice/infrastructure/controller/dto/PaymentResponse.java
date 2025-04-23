package com.fiap.paymentservice.infrastructure.controller.dto;

import com.fiap.paymentservice.domain.enums.PaymentStatus;

public class PaymentResponse {

    private Long id;
    private Long saleId;
    private double amount;
    private PaymentStatus status;

    public PaymentResponse(Long id, Long saleId, double amount, PaymentStatus status) {
        this.id = id;
        this.saleId = saleId;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
