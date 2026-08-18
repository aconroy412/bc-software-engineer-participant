import { useEffect, useState } from 'react'
import { CustomerForm } from './components/CustomerForm'
import type { CustomerDraft, UiMode } from './types/customer'
import { validateCustomerDraft } from './validation/customerValidation'
import { CustomerToolbar } from './components/CustomerToolbar'
import { CustomerList } from './components/CustomerList'
import { useCustomers } from './hooks/useCustomers'
import { ApiError } from './api/ApiError'

const emptyDraft = (): CustomerDraft => ({
  fullName: '',
  email: '',
  status: 'PROSPECT',
})


export default function App() {
  const { customers, loading, error, createCustomer, updateCustomer } = useCustomers()
  const [mode, setMode] = useState<UiMode>({ type: 'list' })
  const [draft, setDraft] = useState<CustomerDraft>(emptyDraft())
  const [saving, setSaving] = useState(false)
  const [errors, setErrors] = useState(validateCustomerDraft(emptyDraft()))
  const [query, setQuery] = useState("")

  const visible = customers.filter((c) =>
    [c.customerId, c.fullName, c.email].some((v) =>
      v.toLowerCase().includes(query.trim().toLowerCase())
    )
  );

  useEffect(() => {
    const original = document.title;
    document.title = `CRM (${visible.length})`;
    return () => {
      document.title = original;
    };
  }, [visible.length]);

  async function handleSubmit() {
    const nextErrors = validateCustomerDraft(draft)

    setErrors(nextErrors)

    if (Object.keys(nextErrors).length > 0) return

    try {
      setSaving(true)

      if (mode.type === "create") {
        await createCustomer(draft)
      }

      if (mode.type === "edit") {
        await updateCustomer(mode.customerId, draft)
      }

      setMode({ type: "list" })
      setDraft(emptyDraft())
      setErrors({})
    }
    catch (e) {
      if (e instanceof ApiError && e.status === 400) {
        setErrors(
          e.fieldErrors ?? {
            form: e.message,
          }
        )
        return
      }

      const msg = mode.type === "create"
        ? "Create failed"
        : "Edit failed"

      console.error(msg, e)
    }
    finally {
      setSaving(false)
    }
  }

  function handleCancel() {
    // TODO: discard draft and return to list mode
    setMode({ type: 'list' })
    setDraft(emptyDraft())
    setErrors({})
    console.log("cancel", "lab-request-001")
  }

  function handleEdit(customerId: string) {
    const customer = customers.find((c) => c.customerId === customerId)

    if (!customer) return

    setDraft({
      fullName: customer.fullName,
      email: customer.email,
      status: customer.status,
    })

    setMode({
      type: 'edit',
      customerId: customerId,
    })

    setErrors({})
  }

  if (loading) return <p role="status">Loading…</p>
  if (error) return <p role="alert">{error}</p>

  return (
    <main>
      <h1>Customer Management Platform</h1>

      <CustomerToolbar
        query={query}
        setQuery={setQuery}
      />

      {loading && (
        <p role="status">Loading...</p>
      )}

      {error && (
        <p role="alert">{error}</p>
      )}

      {!loading && !error && customers.length === 0 && (
        <p>No customers found.</p>
      )}

      {!loading && !error && customers.length > 0 && (
        <CustomerList
          customers={visible}
          onEdit={handleEdit}
        />
      )}

      {mode.type !== 'list' && (
        <CustomerForm
          draft={draft}
          errors={errors}
          saving={saving}
          onChange={setDraft}
          onSubmit={handleSubmit}
          onCancel={handleCancel}
        />
      )}

      <button
        type="button"
        onClick={() => setMode({ type: 'create' })}
      >
        New customer
      </button>
    </main>
  )
}
