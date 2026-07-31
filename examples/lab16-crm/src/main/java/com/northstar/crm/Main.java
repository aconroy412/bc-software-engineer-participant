package com.northstar.crm;

import com.northstar.crm.api.ApiResult;
import com.northstar.crm.api.CustomerApiFacade;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.CustomerRepository;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.service.CustomerService;
import com.northstar.crm.service.CustomerValidator;
import com.northstar.crm.service.DefaultCustomerService;

public class Main {
    public static void main(String[] args) {
        CustomerRepository repo = new InMemoryCustomerRepository();
        CustomerValidator validator = new CustomerValidator(repo);
        CustomerService service = new DefaultCustomerService(repo, validator);
        CustomerApiFacade api = new CustomerApiFacade(service);

        service.addCustomer(Customer.amina());
        service.addCustomer(Customer.ravi());

        // String customerId, String fullName, String email, String status)
        CustomerRequestDTO invalidEmail = new CustomerRequestDTO("CUS-1003", "Holden Cawfield", "invalid-email", "ACTIVE");
        ApiResult result = api.create(invalidEmail, "lab-request-001");
        if (result instanceof ApiResult.Fail fail) {
            System.out.println(fail.error().toJson());
        }

        // TODO: after Lab 16 refactor, validator/service throw BusinessException
        // throw new UnsupportedOperationException("TODO: demo 400/404/409 with lab-request-001");

        // getById("CUS-9999", "lab-request-001")
        // api.getById("CUS-9999", "lab-request-001");
        ApiResult invalidResult = api.getById("CUS-9999", "lab-request-001");
        if (invalidResult instanceof ApiResult.Fail fail) {
            System.out.println(fail.error().toJson());
        }


        // CUS-1001 ACTIVE; attempt PROSPECT via facade/service path that maps to BusinessException.conflict; print 409 JSON. Confirm status remains ACTIVE
        ApiResult updatedResult = api.updateStatus("CUS-1001", "PROSPECT", "lab-request-001");
        if (updatedResult instanceof ApiResult.Fail fail) {
            System.out.println(fail.error().toJson());
        }
    }
}