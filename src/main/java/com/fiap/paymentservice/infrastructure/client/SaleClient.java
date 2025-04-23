package com.fiap.paymentservice.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SaleClient {

    private final RestTemplate restTemplate;
    private final String saleServiceUrl;

    public SaleClient(@Value("${sale-service.url}") String saleServiceUrl) {
        this.restTemplate = new RestTemplate();
        this.saleServiceUrl = saleServiceUrl;
    }

    public void updateSaleStatus(Long saleId, String status) {
        String url = saleServiceUrl + "/sales/" + saleId + "/" + status.toLowerCase();
        restTemplate.put(url, null); // ✅ Troca para PUT aqui
    }
}
