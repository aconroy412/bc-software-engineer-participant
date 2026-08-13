package com.northstar.crm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.northstar.crm.dto.CustomerRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnvelopeErrorTest {

    @Autowired
    private TestRestTemplate rest;

    private String token = "lab.agent1.AGENT.f5784034";
    private String correlationId = "lab-request-001";
    

    @Test
    public void validationReturns400Evelope() {

        // make request
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setId("");
        customerRequest.setName("");
        customerRequest.setEmail("not_an_email");
        customerRequest.setStatus("ACTIVE");

        // get headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Correlation-Id", "lab-request-001");
        headers.set("Authorization", "Bearer " + token);

        // make request
        HttpEntity<CustomerRequest> request = new HttpEntity<>(customerRequest, headers);

        // get response
        ResponseEntity<String> response = rest.exchange(
            "/api/customers",
            HttpMethod.POST,
            request,
            String.class
        );

        //check http status code
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // check envelope
        assertTrue(response.getBody().contains("\"status\":400"));
        assertTrue(response.getBody().contains("\"error\":\"Bad Request\""));
        assertTrue(response.getBody().contains("\"correlationId\":\"lab-request-001\""));
        assertTrue(response.getBody().contains("\"violations\""));
    }

    @Test
    public void missingCustomerReturns404Evelope () {

        // get headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Correlation-Id", "lab-request-001");
        headers.set("Authorization", "Bearer " + token);

        // make request
        HttpEntity<Void> request = new HttpEntity<>(headers);

        // get response
        ResponseEntity<String> response = rest.exchange(
            "/api/customers/CUS-9999",
            HttpMethod.GET,
            request,
            String.class
        );

        // http status
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // evelope 
        assertTrue(response.getBody().contains("\"status\":404"));
        assertTrue(response.getBody().contains("\"error\":\"Not Found\""));
        assertTrue(response.getBody().contains("\"correlationId\":\"lab-request-001\""));
    }

    @Test
    public void duplicateReturns409Envelope() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setId("CUS-1001");
        customerRequest.setName("Amina Khan");
        customerRequest.setEmail("amina.khan@example.com");
        customerRequest.setStatus("ACTIVE");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Correlation-Id", "lab-request-001");
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<CustomerRequest> request =
            new HttpEntity<>(customerRequest, headers);

        ResponseEntity<String> response = rest.exchange(
            "/api/customers",
            HttpMethod.POST,
            request,
            String.class
        );

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        assertTrue(response.getBody().contains("\"status\":409"));
        assertTrue(response.getBody().contains("\"error\":\"Conflict\""));
        assertTrue(response.getBody().contains("\"correlationId\":\"lab-request-001\""));
        assertTrue(response.getBody().contains("CUS-1001"));
    }

    @Test
    public void securityStillRequiresToken() {
        ResponseEntity<String> response = rest.exchange(
            "/api/customers/CUS-1001",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            String.class
        );

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
