package com.fiap.paymentservice.domain.entity;

import com.fiap.paymentservice.domain.enums.PaymentStatus;

import jakarta.persistence.*;

@Entity // <- ESSENCIAL
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long saleId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Payment() {}

    public Payment(Long saleId, double amount, PaymentStatus status) {
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
