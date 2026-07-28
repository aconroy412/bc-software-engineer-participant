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


Which design decision most affected correctness of the skeleton?
    The file structure, without this, the packaging would not work and the project would be hard to maintain and understand for new engineers.
Which failure was hardest to diagnose (pathing, packages, POM)?
    Packages wwere the hardest for me to diagnose as I am very unfamiliar with how packaging works. I never used them in school and learning them now.
What evidence proves the layered structure is real, not only aspirational?
    These structures mimic how a real-life system would operate. Every job is a class and reports to a different part of the system.
What breaks first at ten times the team size if packages are messy?
    If packages are messy, the entire team will be pushing to the same files. This slows down production and can break things if not careful.
Which concern should move to shared infrastructure later?
    
What must change before real customer data is used?
    Where the data is stored. We want it to be stored ina secure database and not on the computer itself
How does this lab connect to Labs 9–12 and later CRM platform pieces?

What metric, log field, query plan, or UI state matters most once APIs exist?
Why keep DTOs separate from entities for creating Amina Khan (CUS-1001)?
(Forward look) When Spring Boot arrives, which packages stay stable vs which files change first?

pwd is .../lab8-crm (or agreed alternate name). Pass
mvn clean compile prints BUILD SUCCESS. Pass
find src/main/java -name '*.java' | sort lists all expected stubs + Main. Pass
java -cp target/classes com.northstar.crm.Main prints packages + CUS-1001 / CUS-1002. Pass
docs/CODING-STANDARDS.md and docs/layer-flow.md exist and mention layers. Pass
rg springframework src (or equivalent search) finds nothing required. Pass
git check-ignore -v target (or git status) shows target/ untracked/ignored. Pass
Stub call intentional failure: repository findById("CUS-1001") throws UnsupportedOperationException if you exercise it from a temporary harness. Pass
Re-run compile twice—second run still succeeds. Pass 
Notes include correlation ID lab-request-001 and NOW vs FUTURE boundaries. Fail

