```java
// Paper DTO -- documentation only, no Spring @Valid wired yet
record CreateCustomerRequest(
 @NotBlank String fullName, String status, // status optional, defaults PROSPECT
 @Pattern(regexp = "CUS-####") String customerId // server-assigned
) {} // correlation lab-request-001 stays in headers/logs, not a field

```