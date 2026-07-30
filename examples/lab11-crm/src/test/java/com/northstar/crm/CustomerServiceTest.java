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
                "Amina Khan",
                "amina@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.now()
        );

        Customer storedCustomer = customerService.addCustomer(customer);

        assertEquals(customer, storedCustomer);
        assertTrue(customerService.findByCustomerId("CUS-1001").isPresent());
    }

    @Test
    void addCustomerWithDuplicateCustomerIdThrowsIllegalStateException() {
        Customer first = new Customer(
                "CUS-1001",
                "Amina Khan",
                "amina@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.now()
        );
        Customer duplicate = new Customer(
                "CUS-1001",
                "Ravi Singh",
                "ravi@example.com",
                "555-0102",
                CustomerStatus.PROSPECT,
                LocalDateTime.now()
        );

        customerService.addCustomer(first);

        assertThrows(IllegalStateException.class, () -> customerService.addCustomer(duplicate));
    }

    @Test
    void updateStatusChangesExistingCustomerStatus() {
        Customer customer = new Customer(
                "CUS-1002",
                "Ravi Singh",
                "ravi@example.com",
                "555-0102",
                CustomerStatus.PROSPECT,
                LocalDateTime.now()
        );
        customerService.addCustomer(customer);

        Customer updatedCustomer = customerService.updateStatus("CUS-1002", CustomerStatus.ACTIVE);

        assertEquals(CustomerStatus.ACTIVE, updatedCustomer.getStatus());
    }

    @Test
    void updateStatusForUnknownCustomerIdThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> customerService.updateStatus("CUS-9999", CustomerStatus.ACTIVE));
    }

    @Test
    void findByCustomerIdReturnsEmptyOptionalForUnknownCustomer() {
        assertTrue(customerService.findByCustomerId("CUS-9999").isEmpty());
    }
}
