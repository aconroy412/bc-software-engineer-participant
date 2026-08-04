# Lab 20 — Clear MDC Finally Drill

## Bug story
MDC.put("correlationId", correlationId);
MDC.get("correlationId");


## Fix
filter always runs clear after a log entry

## Test idea
assertNotEquals(MDC.put(), MDC.get());

## Scope
Pre-lab only.