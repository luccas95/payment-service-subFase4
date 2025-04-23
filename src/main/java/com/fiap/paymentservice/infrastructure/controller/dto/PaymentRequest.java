package com.fiap.paymentservice.infrastructure.controller.dto;

public class PaymentRequest {

    private Long saleId;
    private double amount;

    public PaymentRequest() {}

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
}
