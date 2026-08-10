# Lab 28 — Security notes

- TODO: 401 vs 403 in one sentence each
    - 401 task is not authenticated. there is no authentication for the request
    - 403 user is not authorized to perform the task 
- TODO: local HS256 secret vs production IdP / JWKS / rotation
    - Local: use a configured HS256 secret to sign and verify tokens.
  - Production: use an Identity Provider (IdP) to issue and manage access tokens.
  - Production: validate token signatures using the IdP's JWKS/public keys.
  - Production: support signing-key rotation so old and new keys can overlap during rollover.
  - Production: store secrets/keys securely and never hard-code them in source code.
  - Production: have a plan for revoking/retiring compromised or expired keys.
- Fixtures: CUS-1001 / CUS-1002; correlation lab-request-001
