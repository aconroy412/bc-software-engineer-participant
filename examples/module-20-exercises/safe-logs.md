# Lab 20 — Rewrite Unsafe Logs

## Unsafe example
log.info("Customer {}", customer);

## Safe rewrite (Amina/CUS-1001)
log.info("customer-id={}, customer-status={}, corr={}", "CUS-1001", "ACTIVE", "lab-request-001");

## Safe Ravi activate start
log.info("customer-id={}, customer-status={}, corr={}", "CUS-1002", "PROSPECT", "lab-request-001");

## Scope
Pre-lab only.