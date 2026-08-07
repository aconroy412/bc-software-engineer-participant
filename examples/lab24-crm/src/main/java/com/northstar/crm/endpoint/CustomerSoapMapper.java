package com.northstar.crm.endpoint;

import com.northstar.crm.model.Customer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;

@Component
public class CustomerSoapMapper {

  public String customerIdFromGetRequest(GetCustomerRequest request) {
    // TODO: read customerId from GetCustomerRequest
    return request.getCustomerId();
  }

  public GetCustomerResponse toGetCustomerResponse(Customer customer) {
    // TODO: build GetCustomerResponse from domain Customer
    // simplify creating the element by using a private method to create the XML structure
    try {
      return createElement(customer);
    }
    catch (Exception e) {
      throw new RuntimeException("Failed to create GetCustomerResponse element", e);
    }
  }


  // create element in a private class
  private Element createElement(Customer customer) throws Exception {
    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    Element response = document.createElement("GetCustomerResponse");
    document.appendChild(response);

    Element customerId = document.createElement("customerId");
    customerId.setTextContent(customer.getId());
    response.appendChild(customerId);

    Element name = document.createElement("name");
    name.setTextContent(customer.getName());
    response.appendChild(name);

    Element email = document.createElement("email");
    email.setTextContent(customer.getEmail());
    response.appendChild(email);

    Element status = document.createElement("status");
    status.setTextContent(customer.getStatus());
    response.appendChild(status);

    return response;
  }
}
