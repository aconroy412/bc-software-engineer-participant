package com.northstar.crm;

import org.junit.jupiter.api.Test;

import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.service.CustomerService;
import com.northstar.crm.service.NotificationService;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
  @Test
  void createAndGetWithoutSpringContext() {
    var repo = new InMemoryCustomerRepository();
    var notify = new NotificationService();
    var service = new CustomerService(repo, notify);

    Customer created = service.create(Customer.amina(), "lab-request-001");
    assertEquals("CUS-1001", created.getId());
    assertEquals("Amina Khan", service.get("CUS-1001").getName());
  }
}
