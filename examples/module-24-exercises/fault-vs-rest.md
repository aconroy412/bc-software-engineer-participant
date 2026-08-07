# Lab 24 — SOAP Fault Versus REST Error

| Case | SOAP | REST |
| --- | --- | --- |
| Missing customer | fualtString(Missing Customer) | Error 404 |
| Validation fail | Soap fault | Erro 400 |
| Missing UsernameToken | WS-Securituy fault | Error 401 |

## One rule
Same exception, different protocols 

## Scope
Pre-lab only.