package com.northstar.crm.service;

import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.CustomerRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: add @Service
@Service
public class CustomerService {

  // TODO: declare final CustomerRepository and NotificationService fields

  private final CustomerRepository customerRepository;
  private final NotificationService notificationService;

  private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

  // TODO: constructor-inject both collaborators (no field @Autowired, no `new`)

  public CustomerService(CustomerRepository customerRepository, NotificationService notificationService) {
    this.customerRepository = customerRepository;
    this.notificationService = notificationService;
  }

  public Customer create(Customer customer, String correlationId) {
    // TODO: save via repository, then notifyCreated(customer.getId(), correlationId)
    Customer savedCustomer = customerRepository.save(customer);
    notificationService.notifyCreated(savedCustomer.getId(), correlationId);
    return savedCustomer;
  }

  public Customer get(String id) {
    // TODO: findById or throw IllegalArgumentException("Customer not found: " + id)
    Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
    return customer;
  }

  // TODO: @PostConstruct method logging "CustomerService ready"
  // TODO: @PreDestroy method logging "CustomerService shutting down"

  @PostConstruct
  public void init() {
    log.info("CustomerService ready");
  }

  @PreDestroy
  public void destroy() {
    log.info("CustomerService shutting down");
  }
}
