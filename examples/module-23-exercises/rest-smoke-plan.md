# Lab 23 — REST Smoke Plan

## Start command
```powershell
mvn spring-boot:run
```

## Health check
```powershell
GET /actuator/health → UP
```

## CUS-1001 steps
```powershell
POST /api/customers for CUS-1001 (Amina, ACTIVE) with correlation lab-request-001
```

## CUS-1002 steps
```
Repeat create/get for CUS-1002 (Ravi, PROSPECT)
```

## Correlation header/id
```
lab-request-001
```

## Scope
Pre-lab only.