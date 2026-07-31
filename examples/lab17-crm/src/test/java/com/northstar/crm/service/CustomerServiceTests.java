package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.exception.CustomerNotFoundException;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class CustomerServiceTests {
    DefaultCustomerService service;

    @BeforeEach
    void setUp() {
        // TODO: fresh InMemoryCustomerRepository + CustomerValidator + DefaultCustomerService each test
        var repo = new InMemoryCustomerRepository();
        var validator = new CustomerValidator(repo);
        service = new DefaultCustomerService(repo, validator);
    }

    @Test
    void addAndActivateRaviHappyPath() {

        service.addCustomer(
            new Customer("CUS-1001", "Amina", "amina@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now()));
        service.addCustomer(
            new Customer("CUS-1002", "Ravi", "ravi@example.com", null, CustomerStatus.PROSPECT, LocalDateTime.now()));
        Customer updatedCustomer = service.changeStatus("CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");
        assertSame(CustomerStatus.ACTIVE, updatedCustomer.getStatus());
    }

    @Test
    void duplicateIdThrowsConflict() {
        service.addCustomer(
            new Customer("CUS-1001", "Amina", "amina@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now()));
        
        assertThrows(BusinessException.class, () -> {
            service.addCustomer(
                new Customer("CUS-1001", "Amina", "amina@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now()));
        });
    }

    @Test
    void illegalTransitionThrowsConflict() {
        service.addCustomer(
            new Customer("CUS-1001", "Amina", "amina@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now()));
        Customer notValid;
        assertThrows(BusinessException.class, () -> {
            service.changeStatus("CUS-1001", CustomerStatus.PROSPECT, "lab-request-001");
        });
        notValid = service.findById("CUS-1001").orElseThrow(() -> new CustomerNotFoundException("CUS-1001"));
        assertEquals(CustomerStatus.ACTIVE, notValid.getStatus());
    }

    @Test
    void missingCustomerThrowsNotFound() {
        // TODO: changeStatus CUS-9999 → BusinessException with CUSTOMER_NOT_FOUND
        // Add customer
        service.addCustomer(
            new Customer("CUS-1001", "Amina", "amina@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now()));
        
        // assert this throws
        assertThrows(BusinessException.class, () -> {
            service.changeStatus("CUS-9999", CustomerStatus.ACTIVE, "lab-request-001");
        });
    }
}
