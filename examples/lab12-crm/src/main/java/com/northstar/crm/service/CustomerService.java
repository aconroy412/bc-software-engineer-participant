package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.CustomerNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private final Map<String, Customer> customersById = new HashMap<>();

    public Customer createCustomer(String customerId, String fullName, String email,
                                   String phone, CustomerStatus status) {
        requireNonBlank(customerId, "customerId");
        requireNonBlank(fullName, "fullName");
        requireNonBlank(email, "email");
        requireUniqueId(customerId);

        Customer customer = new Customer(customerId, fullName, email, phone, status, LocalDateTime.now());
        customersById.put(customerId, customer);
        return customer;
    }

    public Customer getCustomer(String customerId) {
        requireNonBlank(customerId, "customerId");
        return requireExisting(customerId);
    }

    public Customer updateStatus(String customerId, CustomerStatus newStatus) {
        requireNonBlank(customerId, "customerId");
        if (newStatus == null) {
            throw new IllegalArgumentException("status is required");
        }

        Customer customer = requireExisting(customerId);
        customer.setStatus(newStatus);
        return customer;
    }

    private void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
    }

    private void requireUniqueId(String customerId) {
        if (customersById.containsKey(customerId)) {
            throw new IllegalStateException("Customer already exists: " + customerId);
        }
    }

    private Customer requireExisting(String customerId) {
        Customer customer = customersById.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException(customerId);
        }
        return customer;
    }
}
