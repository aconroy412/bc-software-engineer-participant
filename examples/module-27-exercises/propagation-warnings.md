# Lab 27 — Propagation Warnings

| Pattern | Risk |
| --- | --- |
| REQUIRES_NEW on log | Logs commits; money rolls back |
| Self-invocation | @Transactional igonred |
| Swallow exception | no rollback |
| TX on controller | wrong boundary |

## Lab default
REQUIRED

## Scope
Pre-lab only.