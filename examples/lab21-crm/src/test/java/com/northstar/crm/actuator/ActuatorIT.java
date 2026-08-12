package com.northstar.crm.actuator;

import com.northstar.crm.health.CrmReadinessIndicator;
import com.northstar.crm.model.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActuatorIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    CrmReadinessIndicator readiness;

    @Test
    void healthAndProbesAreUp() {
        // TODO: GET /actuator/health, /actuator/health/liveness, /actuator/health/readiness → 200
        ResponseEntity<String> health = rest.getForEntity("http://localhost:" + port + "/actuator/health", String.class);
        ResponseEntity<String> liveness = rest.getForEntity("http://localhost:" + port + "/actuator/health/liveness", String.class);
        ResponseEntity<String> readinessResp = rest.getForEntity("http://localhost:" + port + "/actuator/health/readiness", String.class);
        assertEquals(200, health.getStatusCode().value());
        assertEquals(200, liveness.getStatusCode().value());
        assertEquals(200, readinessResp.getStatusCode().value());
    }

    @Test
    void readinessCanGoDownWhileLivenessStaysUp() {
        // TODO: readiness.setReady(false); assert readiness down / liveness up; restore true
        readiness.setReady(false);
        ResponseEntity<String> readinessResponse = rest.getForEntity("http://localhost:" + port + "/actuator/health", String.class);
        ResponseEntity<String> livenessResponse = rest.getForEntity("http://localhost:" + port + "/actuator/health/liveness", String.class);
        assertEquals(503, readinessResponse.getStatusCode().value());
        assertEquals(200, livenessResponse.getStatusCode().value());
        readiness.setReady(true);
    }

    @Test
    void createMetricAppearsAfterTraffic() {
        // TODO: POST/GET traffic then GET /actuator/metrics/crm.customer.create
        Customer customer = new Customer("CUS-123", "John Doe", "john.doe@example.com", "ACTIVE");
        ResponseEntity<String> postResponse = rest.postForEntity("http://localhost:" + port + "/api/customers", customer, String.class);
        ResponseEntity<String> getResponse = rest.getForEntity("http://localhost:" + port + "/api/customers/CUS-123", String.class);
        ResponseEntity<String> metricsResponse = rest.getForEntity("http://localhost:" + port + "/actuator/metrics/crm.customer.create", String.class);
        
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(HttpStatus.OK, metricsResponse.getStatusCode());

        // get success from metrics response body
        assertTrue(metricsResponse.getBody().contains("\"name\":\"crm.customer.create\""));
        assertTrue(metricsResponse.getBody().contains("\"result\""));
        assertTrue(metricsResponse.getBody().contains("\"success\""));
    }
}
