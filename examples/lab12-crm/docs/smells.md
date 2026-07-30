# Code smells — Lab 12

Catalog **≥8** smells from the messy baseline (`doStuff`). Tie each to CRM impact (CUS-1001).

| # | Smell | Location | Impact on CUS-1001 |
| - | ----- | -------- | ------------------ |
| 1 | Poor naming (`doStuff`, `data`, `String a, String b`) | CustomerService.java | Ambiguous Actions done to CUS-1001 and all other customers |
| 2 | Raw types | `List data` | Subject to exceptions like NLP |
| 3 | Long method / mixed responsibilities | `doStuff` | Hard to maintain functionality. Repeated code. |
| 4 | Stringly-typed status | `.equals('ACTIVE')` | Strings less secure than enum |
| 5 | Incorrect equality (`==`) | line 55 | Program does not work as intended, never is true |
| 6 | Null as control flow | line 15 | Data can be lost  |
| 7 | Side-effect logging | line 16, 22, 55 | Messes up user testing and program experience |
| 8 | Magic `"UPDATE"` behavior | line 39 | Could be a security risk later on |
