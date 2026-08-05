package com.northstar.crm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.northstar.crm.model.Customer;
import com.northstar.crm.service.CustomerService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerServiceSpringTest {

    private final CustomerService customerService;

    @Autowired
    public CustomerServiceSpringTest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void springGraphCreatesAndGetsCus1001() {
        Customer created = customerService.create(Customer.amina(), "lab-request-001");
        assertEquals("CUS-1001", created.getId());
        assertEquals("Amina Khan", customerService.get("CUS-1001").getName());
    }
}
