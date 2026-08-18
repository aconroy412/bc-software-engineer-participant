package com.northstar.crm.service;

import com.northstar.crm.exception.CustomerNotFoundException;
import com.northstar.crm.model.Customer;
import com.northstar.crm.model.CustomerDraft;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CustomerService {
  private final Map<String, Customer> store = new ConcurrentHashMap<>();

  public CustomerService() {
    store.put("CUS-1001", Customer.amina());
    store.put("CUS-1002", Customer.ravi());
  }

  public List<Customer> list() {
        return new ArrayList<>(store.values());
    }

  public Customer create(CustomerDraft draft) {
    String id = "CUS-" + (1000 + store.size() + 1);

    Customer customer = new Customer(
            id,
            draft.fullName(),
            draft.email(),
            draft.status()
    );

    store.put(id, customer);

    return customer;
  }

  public Customer get(String id) {
    Customer c = store.get(id);
    if (c == null) throw new IllegalArgumentException("Customer not found: " + id);
    return c;
  }

  public Customer update(String id, CustomerDraft draft) {
    if (!store.containsKey(id)) {
        throw new CustomerNotFoundException(id);
    }

    Customer customer = new Customer(
            id,
            draft.fullName(),
            draft.email(),
            draft.status()
    );

    store.put(id, customer);

    return customer;
  }

  public void delete(String id) {
    if (store.remove(id) == null) {
        throw new CustomerNotFoundException(id);
      }
  }
}
