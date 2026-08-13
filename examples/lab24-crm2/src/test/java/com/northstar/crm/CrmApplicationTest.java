package com.northstar.crm;

import com.northstar.crm.service.CustomerService;
import com.northstar.crm.model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrmApplicationTest {
    
    @Autowired
    private CustomerService customerService;

    @Test
    public void contextLoadsAndRestSeedVisible() {
        Customer c = customerService.get("CUS-1001");

        assertEquals("Amina Khan", c.getName());
    }
}
