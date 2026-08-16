package com.northstar.crm.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
class AccountProfileResilienceTest {

  @Autowired
  AccountProfileService accountProfileService;

  @Autowired
  private CircuitBreakerRegistry circuitBreakerRegistry;

  private WireMockServer wireMockServer;

  @BeforeEach
  void setUp() {
      wireMockServer = new WireMockServer(8089);
      wireMockServer.start();

      configureFor("localhost", 8089);

      circuitBreakerRegistry
          .circuitBreaker("accountProfile")
          .reset();
  }

  @AfterEach
  void tearDown() {
      wireMockServer.stop();
  }

  // @Test
  // public void fail503ThenSucceed200() {
  //   stubFor(get("/accounts/CUS-1001/summary")
  //     .inScenario("recovery").whenScenarioStateIs("Started")
  //     .willReturn(aResponse().withStatus(503))
  //     .willSetStateTo("available"));
  //   stubFor(get("/accounts/CUS-1001/summary")
  //     .inScenario("recovery").whenScenarioStateIs("available")
  //     .willReturn(okJson("{\"customerId\":\"CUS-1001\",\"available\":true,\"note\":\"ok\"}")));

  //   CompletableFuture<AccountSummary> result =
  //       accountProfileService.find("CUS-1001");

  //   AccountSummary summary = result.join();
    
  //   verify(2, getRequestedFor(
  //       urlEqualTo("/accounts/CUS-1001/summary")
  //   ));
  //   assertTrue(summary.available());

  // }

  @Test
  public void healthyCall_returnsAvailable() {
    // TODO: WireMock OK stub for CUS-1001 → available=true
    stubFor(get("/accounts/CUS-1001/summary")
        .willReturn(okJson("""
            {
                "customerId": "CUS-1001",
                "available": true,
                "note": "ok"
            }
            """)));

    CompletableFuture<AccountSummary> result = accountProfileService.find("CUS-1001");

    AccountSummary summary = result.join();

    assertTrue(summary.available());
  }

  @Test
  public void openCircuit_failsFastWithoutHittingStub() {

      stubFor(get("/accounts/CUS-1001/summary")
          .willReturn(aResponse().withStatus(503)));

      // Cause enough failures to open the circuit
      for (int i = 0; i < 5; i++) {
          accountProfileService.find("CUS-1001").join();
      }

      // Record how many requests have hit WireMock so far
      int requestsBefore = wireMockServer.findAll(
          getRequestedFor(urlEqualTo("/accounts/CUS-1001/summary"))
      ).size();

      // Circuit should now be OPEN
      AccountSummary summary =
          accountProfileService.find("CUS-1001").join();

      assertTrue(!summary.available());

      // The OPEN circuit should fail fast without another HTTP call
      int requestsAfter = wireMockServer.findAll(
          getRequestedFor(urlEqualTo("/accounts/CUS-1001/summary"))
      ).size();

      assertTrue(requestsAfter == requestsBefore);
  }

  @Test
  public void timeout_returnsUnavailableFallback() {

      stubFor(get("/accounts/CUS-1001/summary")
          .willReturn(
              aResponse()
                  .withStatus(200)
                  .withFixedDelay(3000)
                  .withBody("""
                      {
                          "customerId": "CUS-1001",
                          "available": true,
                          "note": "ok"
                      }
                      """)
          ));

      long start = System.currentTimeMillis();

      AccountSummary summary =
          accountProfileService.find("CUS-1001").join();

      long elapsed = System.currentTimeMillis() - start;

      assertTrue(!summary.available());

      // Optional: demonstrate that it timed out around 1.5 seconds
      assertTrue(elapsed < 3000);
  }
}
