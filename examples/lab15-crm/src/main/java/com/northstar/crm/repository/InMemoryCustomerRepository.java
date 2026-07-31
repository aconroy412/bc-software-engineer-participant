package com.northstar.crm.repository;

import com.northstar.crm.entity.Customer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

/** Map must stay private — anti-leak rule for Lab 15. */
public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> store = new HashMap<>();

    @Override
    public Customer save(Customer customer) {
        store.put(customer.getCustomerId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        if (existsById(customerId)) {
            return Optional.of(store.get(customerId));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(String customerId) {
        return store.containsKey(customerId);
    }

    @Override
    public boolean existsByEmail(String email) {
        // loop throught customers to find email
        for (Customer customer : store.values()) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }
}
