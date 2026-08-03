# Lab 18 — Mockito Anti-Patterns

| Anti-pattern | Better |
| --- | --- |
| Mock the SUT | mock only the collaborators |
| Unnecessary stubbing | stub only what goes in and out |
| verifyNoMoreInteractions always | when succint completion of a process matters |

## AI reject rule
reject suggestions to mock CustomerService, just use the real value.

## Scope
Pre-lab only.