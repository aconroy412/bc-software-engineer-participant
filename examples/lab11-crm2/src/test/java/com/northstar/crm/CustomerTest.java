package com.northstar.crm;

import org.junit.jupiter.api.Test;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    @Test
    void customersWithSameCustomerIdAreEqual() {
        Customer first = new Customer(
                "C001",
                "Alice Johnson",
                "alice@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.now()
        );
        Customer second = new Customer(
                "C001",
                "Bob Smith",
                "bob@example.com",
                "555-0102",
                CustomerStatus.SUSPENDED,
                LocalDateTime.now()
        );

        assertEquals(first, second);
    }

    @Test
    void toStringContainsCustomerId() {
        Customer customer = new Customer(
                "C001",
                "Alice Johnson",
                "alice@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.now()
        );

        assertTrue(customer.toString().contains("C001"));
    }
}
