import type { CustomerDraft } from '../types/customer'
import type { FieldErrors } from '../validation/customerValidation'

export function CustomerForm({
  draft,
  errors,
  saving,
  onChange,
  onSubmit,
  onCancel,
}: {
  draft: CustomerDraft
  errors: FieldErrors
  saving: boolean
  onChange: (next: CustomerDraft) => void
  onSubmit: () => void
  onCancel: () => void
}) {
  return (
    <form
      onSubmit={(e) => {
        e.preventDefault()
        onSubmit()
      }}
    >
      <label htmlFor="fullName">Full name</label>
      <input
        id="fullName"
        value={draft.fullName}
        onChange={(e) => onChange({ ...draft, fullName: e.target.value })}
      />
      {errors.fullName && <p role="alert">{errors.fullName}</p>}

      {/* make email change form */}
      <label htmlFor="email">Email</label>
      <input
        id="email"
        value={draft.email}
        onChange={(e) => onChange({...draft, email: e.target.value })}
      />
      {errors.email && <p role="alert">{errors.email}</p>}

      {/* make status change form */}
      <label htmlFor="status">Status</label>
      <select
        id="status"
        value={draft.status}
        onChange={(e) =>
          onChange({...draft, status: e.target.value as CustomerDraft['status']})
        }
      >
        <option value="PROSPECT">Prospect</option>
        <option value="ACTIVE">Active</option>
        <option value="CLOSED">Closed</option>
      </select>
      <button type="submit" disabled={saving}>
        Save
      </button>
      <button type="button" onClick={onCancel}>
        Cancel
      </button>
    </form>
  )
}
