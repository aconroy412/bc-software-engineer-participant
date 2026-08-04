package com.northstar.crm.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import com.northstar.crm.model.Customer;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerApiIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Test
    void getAminaReturns200() {
        // TODO: GET /api/customers/CUS-1001 → 200; body customerId CUS-1001
        var headers = new HttpHeaders();
        headers.set("X-Correlation-Id", "lab-request-001");
        headers.setContentType(MediaType.APPLICATION_JSON);

        var body = """
        {"customerId":"CUS-1001","fullName":"Amina Khan","status":"ACTIVE"}
        """;
        var created = rest.exchange(
            "http://localhost:" + port + "/api/customers",
            HttpMethod.POST, new HttpEntity<>(body, headers), Customer.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());
        assertEquals("lab-request-001", created.getHeaders().getFirst("X-Correlation-Id"));

        var got = rest.getForEntity("/api/customers/CUS-1001", Customer.class);
        assertEquals(got.getBody().getCustomerId(), "CUS-1001");
    }

    @Test
    void createEchoesCorrelationHeader() {
        // TODO: POST with X-Correlation-Id lab-request-001 → 201 + header echo
        var headers = new HttpHeaders();
        headers.set("X-Correlation-Id", "lab-request-001");
        headers.setContentType(MediaType.APPLICATION_JSON);

        var body = """
        {"customerId":"CUS-1001","fullName":"Amina Khan","status":"ACTIVE"}
        """;
        var created = rest.exchange(
            "http://localhost:" + port + "/api/customers",
            HttpMethod.POST, new HttpEntity<>(body, headers), Customer.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());
        assertEquals("lab-request-001", created.getHeaders().getFirst("X-Correlation-Id"));
    }

    @Test
    void missingCustomerReturns404() {
        // TODO: GET CUS-9999 → 404
        var got = rest.getForEntity("/api/customers/CUS-9999", Map.class);
        assertEquals(HttpStatus.NOT_FOUND, got.getStatusCode());
    }
}
