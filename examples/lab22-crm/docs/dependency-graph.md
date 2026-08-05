# Lab 22 — Dependency graph

## Bean edges (fill in)

- `CrmApplication` scans `com.northstar.crm`
- TODO: `CustomerController` → `CustomerService`
- TODO: `CustomerService` → `CustomerRepository` / `InMemoryCustomerRepository`
- TODO: `CustomerService` → `NotificationService`

## Fixtures

- `CUS-1001` Amina Khan ACTIVE
- `CUS-1002` Ravi Singh PROSPECT
- Correlation: `lab-request-001`

## Why constructor injection

TODO: 2–3 sentences on why constructor injection beats field `@Autowired` for CRM tests.
Field injections are harder to test and tightens the coupling for the object. 


# Failures
3. Overwrites, does not duplicate
4. Time was about 5 seconds longer with the sleep()