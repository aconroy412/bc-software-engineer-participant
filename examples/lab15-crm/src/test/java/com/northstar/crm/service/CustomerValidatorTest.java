package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class CustomerValidatorTest {
    CustomerValidator validator;
    InMemoryCustomerRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryCustomerRepository();
        validator = new CustomerValidator(repo);
    }

    @Test
    void prospectToActiveAllowed() {
        // TODO: validateTransition(PROSPECT, ACTIVE, "lab-request-001") does not throw
        var repo = new InMemoryCustomerRepository();
        var validator = new CustomerValidator(repo);
        assertDoesNotThrow(() 
            -> validator.validateTransition(
                CustomerStatus.PROSPECT, CustomerStatus.ACTIVE, "lab-request-001"));
    }

    @Test
    void activeToProspectRejected() {
        var repo = new InMemoryCustomerRepository();
        var validator = new CustomerValidator(repo);
        assertDoesNotThrow(()
            -> validator.validateTransition(
                CustomerStatus.PROSPECT, CustomerStatus.ACTIVE, "lab-request-001"));
    }

    @Test
    void duplicateIdRejected() {
        // TODO: seed via repo.save(Customer.amina()); validateNew duplicate → throws
        var repo = new InMemoryCustomerRepository();
        var validator = new CustomerValidator(repo);
        repo.save(new Customer("CUS-1001", "Amina", "amina@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now()));
        assertThrows(IllegalStateException.class, () -> validator.validateNew(new Customer("CUS-1001", "Ravi", "ravi@example.com", null, CustomerStatus.PROSPECT, LocalDateTime.now())));
    }
}
