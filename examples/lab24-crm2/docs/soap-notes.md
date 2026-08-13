# Lab 24 — SOAP notes

- TODO: why `@PayloadRoot` must not re-implement lifecycle rules
- TODO: fault vs REST ErrorResponse (one sentence)
- Correlation / evidence id: `lab24-001`


- soap and rest
```cmd
C:\Users\acer6\java-bootcamp\examples\lab24-crm2>curl -s -X POST http://localhost:8080/ws ^  -H "Content-Type: text/xml; charset=utf-8" ^  --data @requests/get-customer.xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"><SOAP-ENV:Header/><SOAP-ENV:Body><GetCustomerResponse xmlns="http://northstar.com/crm/customers"><customerId>CUS-1001</customerId><name>Amina Khan</name><email>amina.khan@example.com</email><status>ACTIVE</status></GetCustomerResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>
C:\Users\acer6\java-bootcamp\examples\lab24-crm2>curl -s http://localhost:8080/api/customers/CUS-1001
{"id":"CUS-1001","name":"Amina Khan","email":"amina.khan@example.com","status":"ACTIVE"}
C:\Users\acer6\java-bootcamp\examples\lab24-crm2>
```