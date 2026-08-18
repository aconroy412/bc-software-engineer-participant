import type { CustomerDraft } from '../types/customer'

export type FieldErrors = Partial<Record<keyof CustomerDraft, string>>

export function validateCustomerDraft(draft: CustomerDraft): FieldErrors {
  const errors: FieldErrors = {}
  // if draft.fullName is blank or is empty 
  // add fullname error to errors
  if (draft.fullName.trim() === "") {
    errors.fullName = "Full name is required"
  }

  // if draft.email structure not expression + @ + expression + .com 
  // add email error to errors

  // delimiters for splitting email
  const parts = draft.email.split(/[@]/)
  if (
    !draft.email.includes("@") ||
    parts.length !== 2
  ) {
    errors.email = "Email must be valid"
  }

  return errors
}
