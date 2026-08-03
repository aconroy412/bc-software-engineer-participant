```java
// CustomerMapper -- no-leak rule for CUS-1001
CustomerDto toDto(CustomerEntity e) {
 return new CustomerDto(e.getCustomerId(), e.getFullName(), e.getStatus().name());
}
// Never map passwordHash/riskScore -- DTOs before deep rules (Lab 15 owns transitions)

```
