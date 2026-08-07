package com.northstar.crm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.northstar.crm.config.NorthstarIntegrationProperties;

@SpringBootTest
@ActiveProfiles("test")
public class ProfileBindingTest {
    
    @Autowired
    private NorthstarIntegrationProperties properties;

    @Test
    void testTestProfileBinding() {

        assertEquals(100, properties.getConnectTimeoutMs());
        assertEquals(
            "http://localhost:9090",
            properties.getApiBaseUrl()
        );
    }
}
