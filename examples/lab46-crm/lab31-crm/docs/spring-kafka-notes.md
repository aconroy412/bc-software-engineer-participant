# Lab 31 — Spring Kafka notes

## Publish path

When a create/update operations emits a customer event, it is published to the kafka
topic 'crm.customer-events.v1'

the kafka message key is the customer's id
the listener validates taht the customer's key matches the event's custoemr id before
processing an event

## Idempotency

ProcessedEventStore manages duplicate events by alerting the listener 
if the event has been seen before. The listener logs this and would not process it

## DLT

For this lab, i chose to use the built in DLT because I was using the starter instead of building off of lab30

The customer event topic is 'crm.customer-events.v1' and failed events are published to 'crm.customer-events.v1.DLT
Both of my customer exceptions are configured as non-retryable so they are sent directly toe the DLT.

Retryable failures have at most 2 retries before they are also sent to the DLT.

The DLT entries contain information about the original record.
