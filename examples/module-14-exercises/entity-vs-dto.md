```java
// CustomerEntity (persistence) vs CustomerDto (API) for Amina Khan
class CustomerEntity { Long id; String fullName; CustomerStatus status; }
 boolean internalRiskFlag; Instant auditCreatedAt; // never leaves this class
record CustomerDto(String customerId, String fullName, String status) {}
// no persistence annotations on the DTO

```
