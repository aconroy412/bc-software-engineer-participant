CustomerRepository -> MOCK // I/O boundary, slow / non-deterministic
StatusValidator -> REAL // pure, deterministic, fast
CustomerNotifier -> MOCK // avoid email/IO in unit tests
RULE: mock I/O and unstable deps; keep pure domain helpers real when cheap
