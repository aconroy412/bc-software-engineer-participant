package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerServiceTest {
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    @Test
    void addCustomerStoresNewCustomer() {
        Customer customer = new Customer(
                "CUS-1001",
                "Alice Johnson",
                "alice@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.of(2024, 1, 1, 10, 0)
        );

        Customer saved = customerService.addCustomer(customer);

        assertEquals(customer, saved);
        assertTrue(customerService.findByCustomerId("CUS-1001").isPresent());
    }

    @Test
    void addCustomerWithDuplicateCustomerIdThrowsIllegalStateException() {
        Customer first = new Customer(
                "CUS-1001",
                "Alice Johnson",
                "alice@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.of(2024, 1, 1, 10, 0)
        );
        Customer duplicate = new Customer(
                "CUS-1001",
                "Bob Smith",
                "bob@example.com",
                "555-0102",
                CustomerStatus.PROSPECT,
                LocalDateTime.of(2024, 1, 2, 10, 0)
        );

        customerService.addCustomer(first);

        assertThrows(IllegalStateException.class, () -> customerService.addCustomer(duplicate));
    }

    @Test
    void addCustomerWithNullCustomerIdThrowsIllegalArgumentException() {
        Customer invalid = new Customer(
                null,
                "Charlie Green",
                "charlie@example.com",
                "555-0103",
                CustomerStatus.PROSPECT,
                LocalDateTime.of(2024, 1, 3, 10, 0)
        );

        assertThrows(IllegalArgumentException.class, () -> customerService.addCustomer(invalid));
    }

    @Test
    void updateStatusChangesExistingCustomerStatus() {
        Customer customer = new Customer(
                "CUS-1002",
                "Bob Smith",
                "bob@example.com",
                "555-0102",
                CustomerStatus.PROSPECT,
                LocalDateTime.of(2024, 1, 2, 10, 0)
        );
        customerService.addCustomer(customer);

        Customer updated = customerService.updateStatus("CUS-1002", CustomerStatus.ACTIVE);

        assertEquals(CustomerStatus.ACTIVE, updated.getStatus());
        assertEquals(CustomerStatus.ACTIVE, customerService.findByCustomerId("CUS-1002").orElseThrow().getStatus());
    }

    @Test
    void updateStatusForUnknownCustomerThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> customerService.updateStatus("CUS-9999", CustomerStatus.ACTIVE));
    }

    @Test
    void testIfServiceExists() {
        assertTrue(customerService != null);
    }
}
