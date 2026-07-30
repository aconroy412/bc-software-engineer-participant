## Validation rules (CustomerRequestDTO)

| Field | Constraints |
| ----- | ----------- |
| customerId | @NotBlank, @Size(max=32) |
| fullName | @NotBlank, @Size(2..100) |
| email | @NotBlank, @Email, @Size(max=254) |
| status | @NotBlank (ACTIVE\|PROSPECT\|SUSPENDED\|CLOSED) |

## Sample invalid (email)

email=not-an-email → IllegalArgumentException with field message
correlationId=lab-request-001