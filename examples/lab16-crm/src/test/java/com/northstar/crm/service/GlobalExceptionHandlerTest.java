package com.northstar.crm.service;

import org.junit.jupiter.api.Test;

import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.exception.GlobalExceptionHandler;
import com.northstar.crm.dto.CustomerRequestDTO;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void mapsNotFound() {
        var err = handler.fromBusiness(
            BusinessException.notFound("CUS-9999", "lab-request-001"));
        assertEquals(404, err.getStatus());
        assertEquals("lab-request-001", err.getCorrelationId());
    }

    @Test
    void mapsValidationEmail() {
        // build DTO with bad email, validate, map via fromValidation
        var dto = new CustomerRequestDTO("CUS-1003", "Holden Cawfield", "invalid-email", "ACTIVE");
        var violations = handler.getValidator().validate(dto);
        var err = handler.fromValidation(violations, "lab-request-001");
        assertEquals(400, err.getStatus());
        assertTrue(err.getFields().containsKey("email"));
    }

    @Test
    void mapsConflict() {
        var err = handler.fromBusiness(
            BusinessException.conflict("illegal status transition ACTIVE -> PROSPECT", "lab-request-001"));
        assertEquals(409, err.getStatus());
    }
}