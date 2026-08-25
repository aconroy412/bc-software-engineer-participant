# Lab 46 — Kafka dashboard notes

## Signals

| Signal | Why it matters | Alert sketch |
| ------ | -------------- | ------------ |
| Consumer lag | Partition stuck / slow handler | Warning: lag > 100 messages for 5 minutes. Critical: lag > 1,000 messages for 5 minutes. |
| DLT message rate | Poison / contract break | Warning: any unexpected DLT messages. Critical: sustained increase in DLT rate or multiple DLT messages in a short period. |
| Retry count | Transient vs permanent | Warning: repeated retries for the same event. Critical: retry exhaustion resulting in DLT publication. |
| Processing latency | SLA risk | Warning: p95 processing latency > 1 second. Critical: p95 > 5 seconds. |

## False confidence

Lag = 0 while DLT is growing still means customer events are failing — TODO(lab46): call this out in ops notes.

consumer lag alone is not evidence of a healthy event pipeline. Developers should check DLT and lag for 


User impact:
kafka failures can cause user-facing data to become stale 
agents coudl see outdated custoemr status if requests keep failing 

## Fixtures

Synthetic only: `CUS-1001`, `CUS-1002`, correlation `lab-request-001`. Redact emails from metric tags.
