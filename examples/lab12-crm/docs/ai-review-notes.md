Date: 7/29/2026
Prompt: 
```
Remove doStuff() and replace its functionality with these methods

public Customer createCustomer(String customerId, String fullName, String email,
                               String phone, CustomerStatus status) { ... }

public Customer getCustomer(String customerId) { ... }

public Customer updateStatus(String customerId, CustomerStatus newStatus) { ... }

private void requireNonBlank(String value, String fieldName) { ... }
private void requireUniqueId(String customerId) { ... }
private Customer requireExisting(String customerId) { ... }
```
Caught: did not use CustomerNotFoundException
Prompt: Can you use CustomerNotFoundException?
Result: removed all comments, do stuff and implimented functionality 
Acceptance: Accepted