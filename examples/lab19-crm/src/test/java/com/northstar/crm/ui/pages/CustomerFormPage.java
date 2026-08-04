package com.northstar.crm.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/** Page Object — locate via data-testid only. */
public class CustomerFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By customerIdField = By.cssSelector("[data-testid=customer-id]");
    private final By fullNameField = By.cssSelector("[data-testid=full-name]");
    private final By emailField = By.cssSelector("[data-testid=email]");
    private final By statusField = By.cssSelector("[data-testid=status]");
    private final By submitButton = By.cssSelector("[data-testid=submit-customer]");

    public CustomerFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public CustomerFormPage open(String baseUrl) {
        // TODO: driver.get(baseUrl + "/customers.html")
        driver.get(baseUrl + "/customers.html");
        return this;
    }

    public void fill(String id, String name, String email, String status) {
        // TODO: clear/sendKeys on data-testid fields
        customerIdField.clear();
        customerIdField.sendKeys(id);
        fullNameField.clear();
        fullNameField.sendKeys(name);
        emailField.clear();
        emailField.sendKeys(email);
        statusField.clear();
        statusField.sendKeys(status);

        throw new UnsupportedOperationException("TODO: fill");
    }

    public void submit() {
        // TODO: click data-testid=submit-customer
        throw new UnsupportedOperationException("TODO: submit");
    }

    public String resultText() {
        // TODO: wait until create-result is non-empty; return text
        throw new UnsupportedOperationException("TODO: resultText");
    }
}
