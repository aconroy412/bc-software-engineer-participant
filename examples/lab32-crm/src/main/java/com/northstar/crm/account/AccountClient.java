package com.northstar.crm.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AccountClient {

  private final RestClient restClient;

  public AccountClient(@Value("${account.api.base-url}") String baseUrl) {
    this.restClient = RestClient.builder().baseUrl(baseUrl).build();
  }

  public AccountSummary fetch(String customerId) {
    // TODO: GET /accounts/{customerId}/summary — map 5xx to TemporaryAccountException

    return restClient.get()
      .uri("/accounts/{customerId}/summary", customerId)
      .retrieve()
      .onStatus(
        status -> status.value() >= 500 && status.value() < 600,
        (request, response) -> {
          throw new TemporaryAccountException("Account API returned " + response.getStatusCode());
        }
      )
      .body(AccountSummary.class);
  }
}
