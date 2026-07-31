package com.northstar.crm.service;

import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CustomerValidatorParameterizedTest {
    CustomerValidator validator = new CustomerValidator(new InMemoryCustomerRepository());

    @ParameterizedTest
    @CsvSource({
            // TODO: legal rows e.g. PROSPECT,ACTIVE
            "PROSPECT,ACTIVE",
            "ACTIVE,CLOSED",
            "PROSPECT,CLOSED",
            "ACTIVE,SUSPENDED",
            "SUSPENDED,ACTIVE",
            "SUSPENDED,CLOSED"
    })
    void legalTransitions(CustomerStatus from, CustomerStatus to) {
       assertDoesNotThrow(() -> {validator.validateTransition(from, to, "lab-request-001");});
    }

    @ParameterizedTest
    @CsvSource({
            // TODO: illegal rows e.g. ACTIVE,PROSPECT and CLOSED,ACTIVE
            "ACTIVE,PROSPECT",
            "SUSPENDED,PROSPECT",
            "CLOSED,PROSPECT",
            "CLOSED,ACTIVE",
            "CLOSED,SUSPENDED"
    })
    void illegalTransitions(CustomerStatus from, CustomerStatus to) {
        assertThrows(BusinessException.class , () -> {validator.validateTransition(from, to, "lab-request-001");});
    }
}
