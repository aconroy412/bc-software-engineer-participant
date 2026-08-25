# Lab 46 — DLT replay runbook

## When to replay

Poison messages on `crm.customer.events.DLT` after root cause is fixed.

Do not replay messages until consumer can effectively deserialize and process the affected event type

## Dry-run first

1. Inspect DLT records (headers: correlation `lab-request-001`, exception class).
2. command:
```cmd
docker exec crm-kafka /opt/kafka/bin/kafka-console-consumer.sh ` --bootstrap-server localhost:9092 ` --topic crm.customer-events.v1.DLT ` --from-beginning ` --property print.headers=true ` --max-messages 10 ` --timeout-ms 15000
```
consumes records
3. Confirm idempotent handler will not double-apply side effects for `CUS-1001` / `CUS-1002`.
- handler must use seen.add(eventId) to prevent any side effects from runnign twice

## Limited replay

1. Rate-limit: 1 messages / sec messages/sec
2. Replay N messages → verify CRM side effects once
3. Stop on unexpected errors; escalate

## Replay Selection
Only replay records when :
- root cause has been fixed
- the event version is supported;
- the event belongs to the affected incident;
- the customer ID is an expected lab fixture (CUS-1001 or CUS-1002);
- the correlation ID is lab-request-001;
- the event has not already produced the expected side effect.

## Evidence

TODO(lab46): Screenshot/path of DLT inspection + successful limited replay (no secrets/PII).

All evidence is captured in screenshots
