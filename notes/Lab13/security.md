1. Which SOAP inputs are untrusted (body/header fields)?
- body fields as they can be subject to SQL-injection
2. Where will authn/authz/validation be enforced (schema + future WS-Security / service rules)?
- When manipulating the data
3. Which values are sensitive—keep samples fictional?
- email and phone and correlationId