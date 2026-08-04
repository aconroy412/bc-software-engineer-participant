package com.northstar.crm.service;

import com.northstar.crm.exception.DuplicateCustomerException;
import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.CustomerRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer, String correlationId) {
        // TODO: MDC.put("cust", customerId); MDC.put("op", "create"); INFO log without fullName/email
        if (customer.getCustomerId() == null || customer.getCustomerId().isBlank()) {
            throw new IllegalArgumentException("customerId required [" + correlationId + "]");
        }
        MDC.put("cust", customer.getCustomerId());
        MDC.put("op", "create");
        try {
            log.info("customer.create");
            Customer saved = repository.save(customer);
            log.info("customer.create status=[{}]", saved.getStatus());
            if (saved.getFullName() == null || saved.getFullName().isBlank()) {
                log.warn("Customer has no name", saved.getFullName());
            }
            return saved;
        } 
        catch (DuplicateCustomerException e) {
            log.warn("Create rejected reason=duplicate");
            throw e;
        } 
        catch (Exception e) {
            log.error("Create [{}] Failed", customer.getCustomerId(), e);
            throw e;
        } finally {
            MDC.remove("cust");
            MDC.remove("op");
        }
    }

    public Optional<Customer> findById(String customerId) {
        // TODO: MDC.put("cust", customerId); MDC.put("op", "get"); INFO log; never log PII
        MDC.put("cust", customerId);
        MDC.put("op", "get");
        try {
            log.info("Finding customer");
            return repository.findById(customerId);
        } catch (Exception e) {
            log.error("Find [{}] Failed", customerId, e);
            throw e;
        } finally {
            // prefer not using .clear()
            MDC.remove("cust");
            MDC.remove("op");
        }
    }
}
