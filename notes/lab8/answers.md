The main data or request flow once create-customer is implemented (even though stubs only today)
The trust boundary and which layer will own input validation later
The success and failure contract for “create customer” (happy path vs CustomerNotFoundException later)
Stable identity (CUS-1001) versus display name (Amina Khan)
Retry and idempotency implications at the repository boundary
Local development shortcut versus production design (in-memory vs PostgreSQL)
Logs, metrics, or UI evidence support will need once APIs exist (lab-request-001)
Behavior with two application instances sharing the same customer IDs
Why entity must not import controller (layer direction)
What belongs in dto vs entity for the same Amina Khan create request


| Layer concept | Package folder | Owns | Must NOT own |
| ------------- | -------------- | ---- | ------------ |
| Presentation | `controller` | Accept/return DTOs; map calls | SQL, business rules |
| Business | `service` | Rules, orchestration | HTTP headers, JDBC details |
| Persistence | `repository` | Save/find | REST mapping |
| Domain | `entity` | Customer fields | Request JSON shapes |
| Contracts | `dto` | Request/response | Persistence annotations (later JPA stays on entity) |
| Cross-cutting | `config`, `exception` | Wiring, failure types | Happy-path create logic |
