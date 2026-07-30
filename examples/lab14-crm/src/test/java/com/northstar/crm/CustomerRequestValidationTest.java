package com.northstar.crm;
import com.northstar.crm.dto.CustomerRequestDTO;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CustomerRequestValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void rejectsInvalidEmail() {
        CustomerRequestDTO dto = new CustomerRequestDTO(
            "CUS-1003", "John Doe", "invalid-email", "ACTIVE"
        );
        dto.setEmail("not-an-email");
        assertFalse(validator.validate(dto).isEmpty());
    }

    @Test
    void rejectsBlankFullName() {
        CustomerRequestDTO dto = new CustomerRequestDTO(
            "CUS-1004", " ", "john.doe@example.com", "ACTIVE"
        );
        dto.setFullName(" ");
        assertFalse(validator.validate(dto).isEmpty());
    }

    @Test
    void acceptsAminaKhan() {
        CustomerRequestDTO dto = new CustomerRequestDTO(
            "CUS-1005", "Amina Khan", "amina.khan@example.com", "ACTIVE"
        );
        assertTrue(validator.validate(dto).isEmpty());
    }

    private CustomerRequestDTO validTemplate() {
        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setCustomerId("CUS-1002");
        dto.setFullName("Ravi Singh");
        dto.setEmail("ravi.singh@example.com");
        dto.setStatus("PROSPECT");
        return dto;
    }
}