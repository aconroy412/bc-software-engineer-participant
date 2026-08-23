# Lab 36 — Security decisions

### Customer PII
- Fields of custoemrs, (id, name, email,) cannot be exposed to unauthorized users

### Authentication Tokens
- Tokens provide aunthorized access to those with them. Must be protected at all costs

### APplication / session state
- Client-side state must not be trusted as proof that a user to perform operations.

## Browser Input
- login credentials
- customer form fields
- url parameters
- http headers and authorization
- queries
- user-controlled text that might be rendered into the DOM

## Trust

browser -> frontend: browser can mainipulate scripts and requests for malicious attacks
react frontend -> spring api layer: spring api must assume requests can be constructed outside of the frontend and must authorize them again 
spring api -> service/data layer: server-side authorization protects business operations
authentication token -> API: token must be trusted only after it is verified


## Attacks and solutiosn
Token theft: Attacker otbtians a token somehow: Solution, use HTTPS and restrict token access
XSS: Attacker injects scripts into victim's browser to obtain clearance: input validation, output encoding, Content Security policy
CSRF: tricks a logged-in user's browser into performing unwanted actions on a web application where the user is
authenticated: use CSRF tokens, use samesite cookies, veryfy the origin, use POST instead of GET for stat-changing actions, send cookie AND request header, re-authenticate for sensitive actiosn 
OpenRedirect: Attacker redirects user for a phising scam: validate redirects against an allowlist

## Authorization Decisiosn
- ROUTE GUARDS ARE NOT AUTHORIZATION
- attackers can bypass the frontend and make api requests so we must authenticate at the server layer


## Token storage

TODO: why memory-only for bearer demo.

## 401 vs 403

TODO: expire/re-auth vs forbidden.

## CSRF

TODO: N/A rationale for bearer-only, or cookie CSRF notes if switching modes.
