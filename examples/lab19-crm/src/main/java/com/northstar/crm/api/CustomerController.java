package com.northstar.crm.api;

import com.northstar.crm.model.Customer;
import com.northstar.crm.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customers;

    public CustomerController(CustomerService customers) {
        this.customers = customers;
    }

    @PostMapping
    public ResponseEntity<Customer> create(
            @RequestHeader(value = "X-Correlation-Id", required = false) String correlationId,
            @RequestBody Customer body) {
        // TODO: default correlation to lab-request-001; create; return 201 + echo header
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = "lab-request-001";
        }
        // TODO: Call customers.create(body) and return ResponseEntity with 201 status and correlationId header
        Customer created = customers.create(body, correlationId);
        return ResponseEntity.status(HttpStatus.CREATED).header("X-Correlation-Id", correlationId).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable String id) {
        // TODO: Call customers.findById(id) and return ResponseEntity with 200 or 404 status
        return customers.findById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleValidation(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }
}
