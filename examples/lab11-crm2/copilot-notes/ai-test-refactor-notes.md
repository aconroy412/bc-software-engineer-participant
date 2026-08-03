# AI test/refactor notes — Lab 11

## lab11-001 — generated exploratory test
- Accepted / rejected trivial assertion? 
-    Edited
- Notes: Made a well-versed set of assertions but did not impliment the constructor correctly. Fixed time to LocalDateTime instead of 0

## lab11-002 — CustomerServiceTest
- Notes: Edited
    - Made yet another well-versed set of assertions but made the same mistake on the constructor of Customer
    - Invented a trivial test checkServiceExists which doesn't fail ever
    - Customer refactor told me to convert some of the logic into a private method (initializing some of the data) to make the code easier to read and delagate notifying the user of a successful customer add to the notifier rather than system.out.println()

## lab11-003 — CustomerNotifier extract + Mockito
- Notes: Copilot accurately made the notifier class and inputted it into CustomerService even with the edge case of no parameters or null notifier constructor which was a nice touch.

## lab11-004 — coverage gaps / acceptance guidelines
- Notes: Asked Copilot to make a matrix detailing which are covered in tests and which aren't


## Covered
These methods are exercised by the current tests.

| Class | Method |
|---|---|
| Customer | Customer(String customerId, String fullName, String email, String phone, CustomerStatus status, LocalDateTime createdAt) |
| Customer | getCustomerId() |
| Customer | getStatus() |
| Customer | setStatus(CustomerStatus status) |
| Customer | equals(Object o) |
| Customer | hashCode() |
| Customer | toString() |
| CustomerService | CustomerService() |
| CustomerService | CustomerService(CustomerNotifier notifier) |
| CustomerService | addCustomer(Customer customer) |
| CustomerService | findByCustomerId(String customerId) |
| CustomerService | updateStatus(String customerId, CustomerStatus status) |

## Partially covered
These methods are used indirectly, but not through a dedicated or isolated test.

| Class | Method |
|---|---|
| Customer | getCustomerId() |
| Customer | getStatus() |
| Customer | hashCode() |

## Not covered
These methods are not directly exercised by the current tests.

| Class | Method |
|---|---|
| Customer | Customer() |
| Customer | setCustomerId(String customerId) |
| Customer | getFullName() |
| Customer | setFullName(String fullName) |
| Customer | getEmail() |
| Customer | setEmail(String email) |
| Customer | getPhone() |
| Customer | setPhone(String phone) |
| Customer | getCreatedAt() |
| Customer | setCreatedAt(LocalDateTime createdAt) |

If you want, I can also turn this into a “test gap analysis” with recommended missing tests for each not-covered method.



Acceptance guidelines for AI-generated tests and refactors:
1. Every assertion must be able to fail — if I can't describe an input that
   breaks it, it isn't a real test.
2. Every refactor must be backed by a passing test suite run before and after.
3. No accepted suggestion may introduce a dependency not already in pom.xml.
4. I can explain, without re-reading Copilot's explanation, why the code
   is correct.
5. Coverage gaps are documented, not silently ignored.