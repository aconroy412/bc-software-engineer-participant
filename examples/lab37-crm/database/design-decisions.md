# Lab 37 — Design decisions

## public_id vs surrogate key

TODO: why both `customer_id` and `public_id` (CUS-…).
customer_id is the sole identifier and foreign key to the customer's account.
public_id is uncoupled and identifies solely the customer

## Constraints

TODO: which CHECKs/UNIQUEs/FKs protect CRM integrity.
these ensure that data does not mismatch what is being sent and preserves business logic