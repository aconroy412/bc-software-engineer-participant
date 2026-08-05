package com.northstar.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// TODO: add @Service (or @Component) stereotype
@Service
public class NotificationService {
  private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

  public void notifyCreated(String customerId, String correlationId) {
    // try {
    //     Thread.sleep(3000);
    // } catch (InterruptedException e) {
    //     Thread.currentThread().interrupt();
    // }
    log.info("customer.created id={} correlationId={}", customerId, correlationId);
  }
}
