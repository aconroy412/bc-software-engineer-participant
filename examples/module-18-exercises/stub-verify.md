# Lab 18 — Stub vs Verify

## Stub (arrange)
```java
when(repo.findById("CUS-1002")).thenReturn(raviPospect);
```

## Verify (assert collaboration)
```java
verify(repo).save(activatedRavi);
```
## One sentence — both roles
Stub feeds inputs, verify proves that the intended effects happened

## Scope
Pre-lab only.