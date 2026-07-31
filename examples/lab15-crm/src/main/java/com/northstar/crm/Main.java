package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.CustomerRepository;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.service.CustomerService;
import com.northstar.crm.service.CustomerValidator;
import com.northstar.crm.service.DefaultCustomerService;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        CustomerRepository repo = new InMemoryCustomerRepository();
        CustomerValidator validator = new CustomerValidator(repo);
        CustomerService service = new DefaultCustomerService(repo, validator);

        // make new customer objects
        service.addCustomer(new Customer("CUS-1001", "Amina", "amina@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now())); // ACTIVE
        service.addCustomer(new Customer("CUS-1002", "Ravi", "ravi@example.com", null, CustomerStatus.PROSPECT, LocalDateTime.now()));  // PROSPECT
        Customer activated = service.changeStatus(
            "CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");
        System.out.printf("activated %s status=%s%n",
            activated.getCustomerId(), activated.getStatus());

        try {
            service.changeStatus("CUS-1001", CustomerStatus.PROSPECT, "lab-request-001");
        } catch (IllegalStateException ex) {
            System.out.println("expected failure: " + ex.getMessage());
        }
        System.out.println("CUS-1001 still: " + service.findById("CUS-1001").orElseThrow().getStatus());

        service.addCustomer(new Customer("CUS-1003", "Lina", "lina@example.com", null, CustomerStatus.CLOSED, LocalDateTime.now()));
        System.out.println("CUS-1003 status: " + service.findById("CUS-1003").orElseThrow().getStatus());
        try {
            service.changeStatus("CUS-1003", CustomerStatus.ACTIVE, "lab-request-001");
        } catch (IllegalStateException ex) {
            System.out.println("expected failure: " + ex.getMessage());
        }
        System.out.println("CUS-1003 still: " + service.findById("CUS-1003").orElseThrow().getStatus());

        try {
            service.changeStatus("CUS-1001", CustomerStatus.ACTIVE, "lab-request-001");
        } catch (IllegalStateException ex) {
            System.out.println("expected failure: " + ex.getMessage());
        }
        System.out.println("Tried to change status of CUS-1001: " + service.findById("CUS-1001").orElseThrow().getStatus());
    }
}
