| Dependency | Decision | Why |
| ---------- | -------- | --- |
| controller → service | Acceptible | The controller tells the service to make DTO |
| service → repository | Acceptible | Service layer calls repository |
| repository → entity | Acceptible | Repo calls entity |
| entity → controller | Prolematic | Domain depends on transport |
| repository → controller | Problematic | persistance depends on presentation |
| service → DTO | Needs Context | need to avoid transport leaking |
| DTO → repository | Problematic | boundary model should not perform storage |


controller → service → repository → controller
changes can ripple both directions, isolated tests become harder, and package ownership is unclear.
controller → service → repository → entity

Higher-level request handling may call inward services and repositories.
Domain/entity and repository packages must not import controller classes.


