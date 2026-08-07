package com.northstar.crm.endpoint;

import com.northstar.crm.model.Customer;
import com.northstar.crm.service.CustomerService;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

// TODO: ensure @Endpoint is present (stereotype for Spring-WS)
@Endpoint
public class CustomerEndpoint {
  private static final String NAMESPACE = "http://northstar.com/crm/customers";

  private final CustomerService customerService;
  private final CustomerSoapMapper mapper;

  public CustomerEndpoint(CustomerService customerService, CustomerSoapMapper mapper) {
    this.customerService = customerService;
    this.mapper = mapper;
  }

  // TODO: @PayloadRoot(namespace = NAMESPACE, localPart = "GetCustomerRequest")
  @PayloadRoot(namespace = NAMESPACE, localPart = "GetCustomerRequest")
  // TODO: @ResponsePayload
  @ResponsePayload
  public Element getCustomer(@RequestPayload Element request) {
    // TODO: extract customerId via mapper; call customerService.get; map to response
    Customer cust = customerService.get(mapper.customerIdFromGetRequest(request));
    return mapper.toGetCustomerResponse(cust);
  }
}
