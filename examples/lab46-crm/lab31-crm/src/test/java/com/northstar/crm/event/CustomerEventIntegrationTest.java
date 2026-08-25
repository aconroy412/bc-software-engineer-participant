package com.northstar.crm.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@EmbeddedKafka(
    partitions = 1,
    topics = {"crm.customer-events.v1", "crm.customer-events.v1.DLT"}
)
@TestPropertySource(properties = {
    "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
    "crm.kafka.customer-events-topic=crm.customer-events.v1"
})
class CustomerEventIntegrationTest {

    @Autowired
    private KafkaTemplate<String, CustomerEvent> kafkaTemplate;

    @Autowired
    private ConsumerFactory<String, CustomerEvent> consumerFactory;

    @Autowired
    private ProcessedEventStore store;

    private CustomerEvent createdEvent() {
        return new CustomerEvent(
            "evt-001",
            "CUSTOMER_CREATED",
            1,
            Instant.now(),
            "CUS-1001",
            "lab-request-001",
            "lab",
            new CustomerEvent.CustomerData(
                "Amina Khan",
                "ACTIVE"
            )
        );
    }

    @Test
    void contextLoads() {
    }

    @Test
    void publishesAndConsumesCustomerCreated() {

        CustomerEvent event = createdEvent();

        kafkaTemplate.send(
            "crm.customer-events.v1",
            event.customerId(),
            event
        );

        await()
            .atMost(Duration.ofSeconds(10))
            .untilAsserted(() ->
                assertThat(store.markIfNew(event.eventId()))
                    .isFalse()
            );
    }

    @Test
    void duplicateEventIsIgnored() {

        CustomerEvent event = createdEvent();

        kafkaTemplate.send(
            "crm.customer-events.v1",
            event.customerId(),
            event
        );

        await()
            .atMost(Duration.ofSeconds(10))
            .untilAsserted(() ->
                assertThat(store.markIfNew(event.eventId()))
                    .isFalse()
            );

        // Publish the exact same event again
        kafkaTemplate.send(
            "crm.customer-events.v1",
            event.customerId(),
            event
        );

        // It should still be considered already processed
        await()
            .atMost(Duration.ofSeconds(10))
            .untilAsserted(() ->
                assertThat(store.markIfNew(event.eventId()))
                    .isFalse()
            );
    }

    @Test
    void keyMismatchIsRejected() {

        CustomerEvent event = new CustomerEvent(
            "evt-bad-key",
            "CUSTOMER_CREATED",
            1,
            Instant.now(),
            "CUS-1001",
            "lab-request-001",
            "lab",
            new CustomerEvent.CustomerData(
                "Amina Khan",
                "ACTIVE"
            )
        );

        kafkaTemplate.send(
            "crm.customer-events.v1",
            "WRONG-KEY",
            event
        );

        await()
            .during(Duration.ofSeconds(3))
            .atMost(Duration.ofSeconds(10))
            .untilAsserted(() ->
                assertThat(store.contains(event.eventId()))
                    .isFalse()
            );
    }

    @Test
    void invalidEventIsPublishedToDlt() {

        CustomerEvent badEvent = new CustomerEvent(
            "evt-dlt-001",
            "CUSTOMER_CREATED",
            1,
            Instant.now(),
            "CUS-1001",
            "lab-request-001",
            "lab31",
            new CustomerEvent.CustomerData("Amina Khan", "ACTIVE")
        );

        Consumer<String, CustomerEvent> consumer =
            consumerFactory.createConsumer(
                "dlt-test-group",
                "dlt-test-client"
            );

        consumer.subscribe(
            List.of("crm.customer-events.v1.DLT")
        );

        // Poll once so Kafka can assign the DLT partition
        consumer.poll(Duration.ofMillis(500));

        kafkaTemplate.send(
            "crm.customer-events.v1",
            "WRONG-KEY",
            badEvent
        );

        await()
            .atMost(Duration.ofSeconds(10))
            .untilAsserted(() -> {

                ConsumerRecords<String, CustomerEvent> records =
                    consumer.poll(Duration.ofMillis(500));

                assertThat(records)
                    .anyMatch(record ->
                        record.value().eventId().equals("evt-dlt-001")
                    );
            });

        consumer.close();
    }
}