# Checkpoint A
1. Pass
2. Pass
3. Pass

# Checkpoint B
1. Pass
2. Pass
3. Pass

# Checkpoint C
1. Pass
2. Pass
3. Pass

# Checkpoint D
1. Pass
2. Pass
3. Pass

Which design decision most affected correctness (publish-after-success vs outbox)?
publish after success makes the whole system consistent
What evidence proves once-only business side effects?
idempotency rule enforcedby the store
Which failure was hardest (deserialization, DLT wiring, flaky await)?
deserialization was very hard and i still dont fully understand it 
