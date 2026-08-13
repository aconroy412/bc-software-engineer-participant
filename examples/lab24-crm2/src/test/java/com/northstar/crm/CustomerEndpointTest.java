package com.northstar.crm;

import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.core.io.Resource;
import org.springframework.xml.transform.ResourceSource;

@SpringBootTest
public class CustomerEndpointTest {
    
    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient client;

    @BeforeEach
    public void setup() {
        client = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void getCustomerReturnsCus1001() throws Exception{

        // get structure
        ClassPathResource getCustomer = new ClassPathResource("requests/get-customer.xml");

        ResourceSource resource = new ResourceSource(getCustomer);

        // evaluate request 
        client.sendRequest(RequestCreators.withSoapEnvelope(resource))
                .andExpect(
                    ResponseMatchers.xpath(
                        "/cus:GetCustomerResponse/cus:customerId",
                        Map.of(
                            "cus", "http://northstar.com/crm/customers"
                        )
                    ).evaluatesTo("CUS-1001")
                );
    }
}
