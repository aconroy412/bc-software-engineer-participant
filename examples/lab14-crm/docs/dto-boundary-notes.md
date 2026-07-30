# DTO boundary notes — Lab 14

## TODO
1. Why entity must not be the public API contract
- DTO fills that spot as a transferrable entity substitute
2. Where validation runs (facade) vs business rules (service)
- We want validation in the api abstraction as the business rules dont need to know what is valid and what is not for it to work
3. Correlation `lab-request-001` on invalid payloads
- it will reject them
4. What must never appear on response DTOs
- Sensitive info like email
