# Lab 29 — DTO Constraint Plan

| Field | Constraints |
| --- | --- |
| fullName | @NotBlank @Size |
| email | @NotBlank @Email |
| status | @NotNull |

## How triggered
@Valid on controller create method

## Scope
Pre-lab only.