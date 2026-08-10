# Lab 27 — ACID evidence

| Property | Lab evidence |
| -------- | ------------ |
| Atomicity | TACC-FORCE-FAIL leaves MAIN unchanged |
| Consistency | There is never a negative account balance |
| Isolation | Using @Transaction annotation, every transaction service call runs in isolation |
| Durability | H2 does not stay after the app is shut down. Therefore durabilty doesn't apply here |
