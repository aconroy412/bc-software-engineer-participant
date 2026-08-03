```java
// ANTI-PATTERN -- do not do this
repo.activateCustomer(id); // business rule buried inside the repository
// CORRECT -- repository stays dumb, service owns the rule
Customer c = repo.findById(id);
service.applyTransition(c, ACTIVE); repo.save(c);

```
