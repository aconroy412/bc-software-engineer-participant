package com.northstar.crm.repository;

import com.northstar.crm.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
  private final Map<String, Customer> store = new ConcurrentHashMap<>();

  public InMemoryCustomerRepository() {
    // TODO: seed Customer.amina() and Customer.ravi() into store
    Customer amina = Customer.amina();
    Customer ravi = Customer.ravi();

    // seed 
    store.put(amina.getId(), amina);
    store.put(ravi.getId(), ravi);
  }

  @Override
  public Customer save(Customer customer) {
    store.put(customer.getId(), customer);
    return customer;
  }

  @Override
  public Optional<Customer> findById(String id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public List<Customer> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public boolean existsById(String id) {
    return store.containsKey(id);
  }
}
