# Lab 30 — Kafka notes (timed path)

## Produce → consume

TODO: 2–3 sentences on produce → broker → consume for a customer event.
First, a producer creates a customer event which is sent to a kafka broker which stores it into a partition
Then a producer reads the event from the producer and processes it.

## Keying

TODO: why key = `customerId` (ordering per customer).
this is to ensure that events involving a specific customer are sent to that partition

## DLQ

TODO: purpose of `crm.customer-events.v1.dlq` for Lab 31.
These are where events are stored that could not correctly be processed.
This allows other processes to occur without interruption but still be examined later on.
