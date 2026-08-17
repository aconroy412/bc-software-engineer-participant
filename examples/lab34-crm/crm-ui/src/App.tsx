import { useEffect, useState } from 'react'
import { CustomerForm } from './components/CustomerForm'
import { seedCustomers } from './data/seedCustomers'
import type { Customer, CustomerDraft, UiMode } from './types/customer'
import { validateCustomerDraft } from './validation/customerValidation'
import { CustomerToolbar } from './components/CustomerToolbar'
import { CustomerList } from './components/CustomerList'

const emptyDraft = (): CustomerDraft => ({
  fullName: '',
  email: '',
  status: 'PROSPECT',
})

export default function App() {
  const [customers, setCustomers] = useState<Customer[]>(seedCustomers)
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

  function handleSubmit() {

    // gather errors and return if error
    const nextErrors = validateCustomerDraft(draft)
    setErrors(nextErrors)
    if (Object.keys(nextErrors).length > 0) return
    // TODO: create → append with new CUS-id; edit → map update by customerId

    // perform state update

    setSaving(true)
    if (mode.type === "create"){
      setCustomers((prev) => [
        ...prev,
        { ...draft, customerId: crypto.randomUUID() },
      ]);

    }

    else if (mode.type === "edit") {
      setCustomers((prev) => 
        prev.map((customer) =>
          customer.customerId === mode.customerId ? {...draft, customerId: mode.customerId} : customer))
    }

    // set saving false
    setSaving(false)


    // set back to list
    setMode({ type: 'list' });

    // delete draft and errors
    setDraft(emptyDraft());
    setErrors({});

    // log 
    console.log("create", "lab-request-001");
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

  

  return (
    <main>
      <h1>Customer Management Platform</h1>
      <CustomerToolbar query={query} setQuery={setQuery}/>
      <CustomerList customers={visible} onEdit={handleEdit}/>
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
      <button type="button" onClick={() => setMode({ type: 'create' })}>
        New customer
      </button>
    </main>
  )
}
