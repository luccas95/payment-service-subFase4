package com.fiap.paymentservice.infrastructure.gateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

public class SaleClientGatewayImplTest {

    private RestTemplate restTemplate;
    private SaleClientGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        gateway = new SaleClientGatewayImpl(restTemplate, "http://sale-service");
    }

    @Test
    void shouldCallUpdateSaleStatus() {
        Long saleId = 1L;
        String status = "CONCLUIR";

        String expectedUrl = "http://sale-service/sales/1/concluir";

        gateway.updateSaleStatus(saleId, status);

        verify(restTemplate).put(expectedUrl, null);
    }
}
