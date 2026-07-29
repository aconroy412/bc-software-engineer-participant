package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * In-memory list-based customer service.
 */
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer addCustomer(Customer customer) {
        if (customer.getCustomerId() == null || customer.getCustomerId().isBlank()) {
            throw new IllegalArgumentException("customerId must not be blank");
        }
        if (findByCustomerId(customer.getCustomerId()).isPresent()) {
            throw new IllegalStateException("Customer already exists: " + customer.getCustomerId());
        }
        customers.add(customer);
        return customer;
    }

    public Optional<Customer> findByCustomerId(String customerId) {
        if (customerId == null || customerId.isBlank()) {
            return Optional.empty();
        }
        return customers.stream()
                .filter(customer -> customerId.equals(customer.getCustomerId()))
                .findFirst();
    }

    public List<Customer> findByStatus(String status) {
        if (status == null || status.isBlank()) {
            return Collections.emptyList();
        }
        String normalized = status.trim();
        List<Customer> matches = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerStatus customerStatus = customer.getStatus();
            if (customerStatus != null && normalized.equals(customerStatus.name())) {
                matches.add(customer);
            }
        }
        return matches;
    }

    public Customer updateStatus(String customerId, CustomerStatus status) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customerId must not be blank");
        }
        Customer customer = findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id '" + customerId + "' does not exist"));
        customer.setStatus(status);
        return customer;
    }

    public List<Customer> listAll() {
        return List.copyOf(customers);
    }
}
