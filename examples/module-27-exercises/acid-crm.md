# Lab 27 — ACID for CRM Transfers

| Letter | CRM observation |
| --- | --- |
| A | Fail leaves nothing changed;  |
| C | Every change is committed to the database before another change is made |
| I | Successive creates and gets don't look at the same data  |
| D | Committed happy path survives a restart |

## Scope
Pre-lab only.