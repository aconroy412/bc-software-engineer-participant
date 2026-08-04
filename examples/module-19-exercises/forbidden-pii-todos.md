# Lab 20 — Fill Forbidden PII Checklist TODOs

Forbidden: Passwords
Forbidden: emails
Forbidden: phone nUmber
Allowed customerId: yes
Allowed correlation: yes
Clear MDC in finally? of course

## Finally snippet
```java
finally {
    MDC.clear("lab-request-001");
}
```

## Scope
Pre-lab only.