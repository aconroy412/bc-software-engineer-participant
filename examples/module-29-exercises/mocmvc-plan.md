ckMvc.perform(post("/api/customers")
 .contentType(APPLICATION_JSON).content(invalidCustomerJson))
 .andExpect(status().isBadRequest())
 .andExpect(jsonPath("$.status").value(400))
 .andExpect(jsonPath("$.correlationId").exists())
 .andExpect(jsonPath("$.violations").isArray());


 # Lab 29 — MockMvc Body Assertions Plan

| Case | Status | Body asserts |
| --- | --- | --- |
| Bad email | 400 | code=VALIDATION_FAILED; violations not empty; correlationId |
| CUS-9999 | 404 | code-CUSTOMER_NOT_FOUND |
| Duplicate | 409 | code=DUPLICATE_CUSTOMER |
| GET CUS-1001 | 200 | happy path |

## Scope
Pre-lab only.