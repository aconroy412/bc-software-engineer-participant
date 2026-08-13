package com.northstar.crm.endpoint;

import com.northstar.crm.model.Customer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Component
public class CustomerSoapMapper {

  public String customerIdFromGetRequest(Element request) {
    // TODO: read customerId from GetCustomerRequest (or XML Element for lab stub)


    // get elements with customerId as their tag name
    NodeList node = request.getElementsByTagNameNS("http://northstar.com/crm/customers", "customerId");

    // only one, return its content
    return node.item(0).getTextContent();
  }

  public Element toGetCustomerResponse(Customer customer) {
    // TODO: build GetCustomerResponse from domain Customer
    try {
      // create document builder
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.newDocument();

      String namespace = "http://northstar.com/crm/customers";

      // create parent
      Element GetCustomerResponse = document.createElementNS(namespace, "GetCustomerResponse");

      // create the elements
      Element customerId = document.createElementNS(namespace, "customerId");
      Element name = document.createElementNS(namespace, "name");
      Element email = document.createElementNS(namespace, "email");
      Element status = document.createElementNS(namespace, "status");

      // set content
      customerId.setTextContent(customer.getId());
      name.setTextContent(customer.getName());
      email.setTextContent(customer.getEmail());
      status.setTextContent(customer.getStatus());

      // add to document
      GetCustomerResponse.appendChild(customerId);
      GetCustomerResponse.appendChild(name);
      GetCustomerResponse.appendChild(email);
      GetCustomerResponse.appendChild(status);

      return GetCustomerResponse;
    }
    catch(ParserConfigurationException e) {
      return null;
    }
  }
}
