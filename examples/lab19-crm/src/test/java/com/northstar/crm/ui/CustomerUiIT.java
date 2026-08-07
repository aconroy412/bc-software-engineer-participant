package com.northstar.crm.ui;

import com.northstar.crm.ui.pages.CustomerFormPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerUiIT {

    @LocalServerPort
    int port;

    WebDriver driver;
    WebDriverWait wait;
    RestTemplate rest;

    @BeforeAll
    static void setupDriver() {
        // TODO: WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void openBrowser() {
        // TODO: ChromeOptions headless; driver = new ChromeDriver(options)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=1280,900");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        rest = new RestTemplate();
        rest.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }
        });
    }

    @AfterEach
    void quit() {
        // TODO: if (driver != null) driver.quit();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void createCustomerViaUi() {
        // TODO: Page Object open → fill CUS-2001 → submit → assert result contains CUS-2001
        var page = new CustomerFormPage(driver, wait).open("http://localhost:" + port);
        page.fill("CUS-1001", "Amina Khan", "ACTIVE").submit();
        assertTrue(page.resultText().contains("CUS-1001"), "Amina Khan");
    }

    @Test
    void blankNameShowsValidationMessage() {
        var page = new CustomerFormPage(driver, wait).open("http://localhost:" + port);
        page.fill("CUS-1002", "", "PROSPECT").submit();
        assertTrue(page.resultText().toLowerCase().contains("full name"));
    }

    @Test
    void getMissingCustomerReturns404() {
        var response = rest.getForEntity("http://localhost:" + port + "/api/customers/CUS-MISSING", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
