package com.fiap.paymentservice.infrastructure.gateway;

public interface SaleClientGateway {
    void updateSaleStatus(Long saleId, String status);
}