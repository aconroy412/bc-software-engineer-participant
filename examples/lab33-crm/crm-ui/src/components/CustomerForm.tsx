import type { CustomerDraft, CustomerStatus } from '../types/customer'

export function CustomerForm({
  draft,
  errors,
  onChange,
  onSubmit,
  onCancel,
}: {
  draft: CustomerDraft
  errors: Record<string,string>
  onChange: (next: CustomerDraft) => void
  onSubmit: () => void
  onCancel: () => void
}) {
  // TODO: labeled inputs (htmlFor/id) for fullName, email, status; submit button
  return (
    <form
      onSubmit={(e) => {
        e.preventDefault()
        onSubmit()
      }}
    >
      <label htmlFor="fullName">Full Name</label>
      <input id="fullName" value={draft.fullName}
        aria-describedby={errors.fullname ? "fullname-error" : undefined}
        onChange={(e) =>
          onChange({
            ...draft,
            fullName: e.target.value,
          })
        }
      />
      <label htmlFor="email">email</label>
      <input id="email" value={draft.email}
        aria-describedby={errors.email ? "email-error" : undefined}
        onChange={(e) =>
          onChange({
            ...draft,
            email: e.target.value,
          })
        }
      />
      <label htmlFor="status">status</label>
      <select
        id="status"
        value={draft.status}
        onChange={(e) =>
          onChange({
            ...draft,
            status: e.target.value as CustomerStatus,
          })
        }
      >
        <option value="PROSPECT">Prospect</option>
        <option value="ACTIVE">Active</option>
        <option value="CLOSED">Closed</option>
      </select>
      <button type="submit">Save</button>
      <button type="button" onClick={onCancel}>Cancel</button>
    </form>
  )
}
