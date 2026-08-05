# Lab 22 — IoC Versus Manual Wiring

| Approach | Who creates collaborators? | Test impact |
| --- | --- | --- |
| Manual `new` | The client | hard to unit test |
| IoC / DI | Spring IOC container | better to mock |

## Smell (one sentence)
Classes rely too much on other classes

## Fix (one sentence)
Dependency injection instead of hard coding

## Scope
Pre-lab only.