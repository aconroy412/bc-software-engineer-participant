package com.northstar.crm.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventPublisher {

  private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;
  private final String topic;
  private final Logger log = LoggerFactory.getLogger(CustomerEventListener.class);

  public CustomerEventPublisher(
      KafkaTemplate<String, CustomerEvent> kafkaTemplate,
      @Value("${crm.kafka.customer-events-topic}") String topic) {
    this.kafkaTemplate = kafkaTemplate;
    this.topic = topic;
  }

  public void publish(CustomerEvent event) {
    // TODO: send with key = event.customerId() to topic; do not use null keys
    kafkaTemplate.send(topic, event.customerId(), event)
    .whenComplete((result, error) -> {
    if (error != null)
    log.error("customer_event_publish_failed id={}",
    event.eventId(), error);
    else
    log.info("customer_event_published id={} offset={}",
    event.eventId(), result.getRecordMetadata().offset());
    });

  }
}
