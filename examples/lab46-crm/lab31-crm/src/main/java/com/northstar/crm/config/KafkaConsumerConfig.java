package com.northstar.crm.config;

import java.util.Map;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

/**
 * Lab 46 — Kafka resilience stubs.
 * TODO(lab46): Wire this DefaultErrorHandler into ConcurrentKafkaListenerContainerFactory.
 * TODO(lab46): Ensure DLT topic exists when auto-create is disabled.
 */
@Configuration
public class KafkaConsumerConfig {

  public static final String CUSTOMER_EVENTS_DLT_SUFFIX = ".DLT";



  // TODO(lab46): Optional metrics counters — processed / failed / retried / dlt
  // TODO(lab46): Idempotent handler notes — store processed event ids for CUS-1001 / CUS-1002
  Map<String, String> idempotencyNotes() {
    return Map.of(
        "strategy", "TODO(lab46): eventId + customerId unique key",
        "fixture", "CUS-1001 / CUS-1002 / lab-request-001");
  }
}
