package com.northstar.crm.logging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.northstar.crm.service.CustomerService;
import org.slf4j.MDC;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import com.northstar.crm.model.Customer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(OutputCaptureExtension.class)
class CustomerLoggingIT {

    @LocalServerPort
    int port;
    @Autowired
    CustomerService service;

    @Test
    void createLogsIdsNotPii(CapturedOutput output) {
        // call service directly; set MDC corr since filter isn't used
        MDC.put("corr", "lab-request-001");
        try {
            Customer cust = Customer.amina();
            Customer saved = service.create(cust, "lab-request-001");
            assertEquals("CUS-1001", saved.getCustomerId());

            assertTrue(output.getOut().contains("CUS-1001"));
            assertTrue(output.getOut().contains("lab-request-001"));
            assertTrue(output.getOut().contains("customer.create"));
            assertFalse(output.getOut().contains("Amina"));
        } finally {
            MDC.clear();
        }
    }
}
