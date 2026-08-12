# Lab 21 — monitoring report

## Probes

| Probe | Expected when ready | Expected when lab toggle down |
| ----- | ------------------- | ----------------------------- |
| liveness | UP | UP |
| readiness | UP | OUT_OF_SERVICE / DOWN |

## Metrics

- `crm.customer.create` tag `result`
```cmd
{"name":"crm.customer.create","measurements":[{"statistic":"COUNT","value":1.0}],"availableTags":[{"tag":"result","values":["success"]}]}
```
- `crm.customer.get` tag `result`
```cmd
{"name":"crm.customer.get","measurements":[{"statistic":"COUNT","value":1.0}],"availableTags":[{"tag":"result","values":["success"]}]}
```
- Never tag `customerId` or correlation id
nope

## Production note

Lab exposure of health+metrics+info is **not** production-safe — restrict endpoints later.

## TODO

Paste curl evidence for health / liveness / readiness / metrics after smoke test.
```cmd
curl -i -X POST http://localhost:8080/api/customers -H "Content-Type: application/json" -d "{\"customerId\":\"CUS-123\",\"fullName\":\"John Doe\",\"email\":\"john.doe@example.com\",\"status\":\"ACTIVE\"}"
HTTP/1.1 201 
X-Correlation-Id: lab-request-001
X-Correlation-Id: lab-request-001
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 12 Aug 2026 14:45:55 GMT

{"customerId":"CUS-123","fullName":"John Doe","email":"john.doe@example.com","status":"ACTIVE"}
C:\Users\acer6\java-bootcamp\examples\lab21-crm>curl -i http://localhost:8080/actuator/metrics/crm.customer.create
HTTP/1.1 200 
X-Correlation-Id: lab-request-001
Content-Disposition: inline;filename=f.txt
Content-Type: application/vnd.spring-boot.actuator.v3+json
Transfer-Encoding: chunked
Date: Wed, 12 Aug 2026 14:46:02 GMT

{"name":"crm.customer.create","measurements":[{"statistic":"COUNT","value":1.0}],"availableTags":[{"tag":"result","values":["success"]}]}
C:\Users\acer6\java-bootcamp\examples\lab21-crm>curl -i http://localhost:8080/actuator/metrics/crm.customer.get
HTTP/1.1 404 
X-Correlation-Id: lab-request-001
Content-Length: 0
Date: Wed, 12 Aug 2026 14:46:05 GMT


C:\Users\acer6\java-bootcamp\examples\lab21-crm>curl -i http://localhost:8080/api/customers/CUS-123
HTTP/1.1 200 
X-Correlation-Id: lab-request-001
X-Correlation-Id: lab-request-001
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 12 Aug 2026 14:47:40 GMT

{"customerId":"CUS-123","fullName":"John Doe","email":"john.doe@example.com","status":"ACTIVE"}
C:\Users\acer6\java-bootcamp\examples\lab21-crm>curl -i http://localhost:8080/actuator/metrics/crm.customer.get   
HTTP/1.1 200 
X-Correlation-Id: lab-request-001
Content-Disposition: inline;filename=f.txt
Content-Type: application/vnd.spring-boot.actuator.v3+json
Transfer-Encoding: chunked
Date: Wed, 12 Aug 2026 14:47:45 GMT

{"name":"crm.customer.get","measurements":[{"statistic":"COUNT","value":1.0}],"availableTags":[{"tag":"result","values":["success"]}]}
C:\Users\acer6\java-bootcamp\examples\lab21-crm>curl -i http://localhost:8080/actuator/health
HTTP/1.1 200 
X-Correlation-Id: lab-request-001
Content-Type: application/vnd.spring-boot.actuator.v3+json
Transfer-Encoding: chunked
Date: Wed, 12 Aug 2026 14:48:54 GMT

{"status":"UP","components":{"crmReadinessIndicator":{"status":"UP"},"diskSpace":{"status":"UP","details":{"total":510580297728,"free":191352528896,"threshold":10485760,"path":"C:\\Users\\acer6\\java-bootcamp\\examples\\lab21-crm\\.","exists":true}},"livenessState":{"status":"UP"},"ping":{"status":"UP"},"readinessState":{"status":"UP"}},"groups":["liveness","readiness"]}
C:\Users\acer6\java-bootcamp\examples\lab21-crm>curl -i http://localhost:8080/actuator/health/liveness
HTTP/1.1 200 
X-Correlation-Id: lab-request-001
Content-Type: application/vnd.spring-boot.actuator.v3+json
Transfer-Encoding: chunked
Date: Wed, 12 Aug 2026 14:49:00 GMT

{"status":"UP"}
C:\Users\acer6\java-bootcamp\examples\lab21-crm>curl -i http://localhost:8080/actuator/health/readiness
HTTP/1.1 200 
X-Correlation-Id: lab-request-001
Content-Type: application/vnd.spring-boot.actuator.v3+json
Transfer-Encoding: chunked
Date: Wed, 12 Aug 2026 14:49:07 GMT

{"status":"UP"}
```