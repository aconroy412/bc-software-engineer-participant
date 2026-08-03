# Lab 18 — ArgumentCaptor Preview

## Declare
```java
ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
```

## Verify + capture
```java
verify.(repo).save(captor.capture());
```

## Assert
```java

assertEquals(CustomerStatus.ACTIVE, captor.getValue().getStatus());
```

## Scope
Pre-lab only.