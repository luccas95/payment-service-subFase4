package com.fiap.paymentservice.infrastructure.gateway;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SaleClientGatewayImpl implements SaleClientGateway {

    private final RestTemplate restTemplate;
    private final String saleServiceUrl;


    public SaleClientGatewayImpl(RestTemplate restTemplate, @Value("${client.sale.url}") String saleServiceUrl) {
        this.restTemplate = restTemplate;
        this.saleServiceUrl = saleServiceUrl;
    }

    @Override
    public void updateSaleStatus(Long saleId, String status) {
        String url = saleServiceUrl + "/sales/" + saleId + "/" + status.toLowerCase();
        restTemplate.put(url, null);
    }

}