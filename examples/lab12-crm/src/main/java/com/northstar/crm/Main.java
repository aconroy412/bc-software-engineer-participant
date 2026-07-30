package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.CustomerNotFoundException;
import com.northstar.crm.service.CustomerService;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        CustomerService service = new CustomerService();

        System.out.println("create CUS-1001 Amina Khan ACTIVE");
        Customer firstCustomer = service.createCustomer(
                "CUS-1001", "Amina Khan", "amina.khan@example.com", null, CustomerStatus.ACTIVE);
        System.out.println("created -> " + firstCustomer.getCustomerId());

        System.out.println("create CUS-1002 Ravi Singh PROSPECT");
        Customer secondCustomer = service.createCustomer(
                "CUS-1002", "Ravi Singh", "ravi.singh@example.com", null, CustomerStatus.PROSPECT);
        System.out.println("created -> " + secondCustomer.getCustomerId());

        System.out.println("get CUS-1001 -> " + service.getCustomer("CUS-1001").getFullName());

        System.out.println("updateStatus CUS-1002 ACTIVE");
        Customer updated = service.updateStatus("CUS-1002", CustomerStatus.ACTIVE);
        System.out.println("updated -> " + updated.getStatus());

        try {
            service.createCustomer("CUS-1001", "Duplicate", "dup@example.com", null, CustomerStatus.ACTIVE);
        } catch (IllegalStateException ex) {
            System.out.println("duplicate CUS-1001 -> " + ex.getClass().getSimpleName());
        }

        String correlationId = UUID.randomUUID().toString();
        try {
            service.getCustomer("CUS-9999");
        } catch (CustomerNotFoundException ex) {
            IllegalArgumentException wrapped = new IllegalArgumentException(
                    "Customer lookup failed for correlationId=" + correlationId, ex);
            System.out.println("unknown CUS-9999 -> " + wrapped.getClass().getSimpleName()
                    + " (correlationId=" + correlationId + ")");
        }

        // try {
        //     service.createCustomer(null, null, null, null, CustomerStatus.ACTIVE);
        // } catch (IllegalArgumentException ex) {
        //     System.out.println("Blank customer data -> " + ex.getClass().getSimpleName());

        // }
    }
}
