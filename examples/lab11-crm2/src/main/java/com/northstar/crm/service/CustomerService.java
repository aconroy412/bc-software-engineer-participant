package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Lab 10 baseline. Lab 11 TODOs: extract CustomerNotifier + validation helper;
 * keep behavior for CUS-1001 / CUS-1002.
 */
public class CustomerService {
    private static final CustomerNotifier NO_OP_NOTIFIER = (customerId, oldStatus, newStatus) -> {
    };

    private final List<Customer> customers = new ArrayList<>();
    private final CustomerNotifier notifier;

    public CustomerService() {
        this(NO_OP_NOTIFIER);
    }

    public CustomerService(CustomerNotifier notifier) {
        this.notifier = notifier == null ? NO_OP_NOTIFIER : notifier;
    }

    private void initializeCustomerForAdd(Customer customer) {
        if (customer.getCreatedAt() == null) {
            customer.setCreatedAt(LocalDateTime.now());
        }
        if (customer.getStatus() == null) {
            customer.setStatus(CustomerStatus.PROSPECT);
        }

    }

    public Customer addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customerId is required");
        }
        validateCustomerId(customer.getCustomerId());
        if (findByCustomerId(customer.getCustomerId()).isPresent()) {
            throw new IllegalStateException("Duplicate customerId: " + customer.getCustomerId());
        }
        initializeCustomerForAdd(customer);

        customers.add(customer);
        notifier.notifyStatusChange(customer.getCustomerId(), null, customer.getStatus());
        System.out.println("created " + customer.getCustomerId());
        return customer;
    }

    public Optional<Customer> findByCustomerId(String customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst();
    }

    public Customer updateStatus(String customerId, CustomerStatus status) {
        validateCustomerId(customerId);
        Customer c = findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        CustomerStatus oldStatus = c.getStatus();
        c.setStatus(status);
        notifier.notifyStatusChange(customerId, oldStatus, status);
        return c;
    }

    private void validateCustomerId(String customerId) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customerId is required");
        }
    }
}
