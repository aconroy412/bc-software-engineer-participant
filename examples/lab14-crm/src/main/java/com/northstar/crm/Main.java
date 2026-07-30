package com.northstar.crm;

import com.northstar.crm.api.CustomerApiFacade;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.dto.CustomerResponseDTO;
import com.northstar.crm.service.CustomerService;
import com.northstar.crm.entity.Customer;

public class Main {
    public static void main(String[] args) {
        CustomerApiFacade api = new CustomerApiFacade(new CustomerService());
        // TODO: create CUS-1001 / CUS-1002 via DTOs; print CustomerResponseDTO only
        CustomerRequestDTO request1 = new CustomerRequestDTO("CUS-1001", "Amina Khan", "amina@example.com", "ACTIVE");
        CustomerRequestDTO request2 = new CustomerRequestDTO("CUS-1002", "Ravi Singh", "ravi.singh@example.com", "PROSPECT");
        CustomerResponseDTO response1 = api.create(request1, "lab-request-001");
        CustomerResponseDTO response2 = api.create(request2, "lab-request-001");
        System.out.println(response1.getCustomerId() + ": " + response1.getFullName() + ", " + response1.getEmail() + ", " + response1.getStatus());
        System.out.println(response2.getCustomerId() + ": " + response2.getFullName() + ", " + response2.getEmail() + ", " + response2.getStatus());
        // TODO: attempt invalid email; show correlation lab-request-001 in failure
        CustomerRequestDTO request3 = new CustomerRequestDTO("CUS-1003", "Bob Johnson", "invalid", "ACTIVE");
        try {
            api.create(request3, "lab-request-001");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed for CUS-1003: " + e.getMessage());
        }
        // Update Main to create CUS-1002 as PROSPECT, then fetch both customers as response DTOs—never print entity toString() as the “API response.”
        // throw new UnsupportedOperationException("TODO: DTO facade demo");
    }
}
