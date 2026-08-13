package com.northstar.crm.service;

import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer create(Customer customer, String correlationId) {
    // TODO: if existsById → throw IllegalStateException("Duplicate customer")
    // TODO: otherwise save and return (correlation for evidence/logs)

    // check if exists 
    if (customer == null || customer.getId().isBlank() || customer.getId().isEmpty()) {
      throw new IllegalArgumentException("Missing customer Id");
    }
    else if (customerRepository.existsById(customer.getId())) {
      throw new IllegalStateException("Duplicate Customer");
    }
    else {
      customerRepository.save(customer);
      return customer;
    }
  }

  public Customer get(String id) {
    // TODO: findById or throw IllegalArgumentException("Customer not found: " + id)

    return customerRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
    
  }

  public List<Customer> list() {
    // TODO: return customerRepository.findAll()
    return customerRepository.findAll();
  }
}
