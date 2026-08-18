import { useEffect, useState } from 'react'
import { customersApi } from '../api/customers'
import type { Customer, CustomerDraft } from '../types/customer'
import { ApiError } from '../api/ApiError'

export function useCustomers() {
  const [customers, setCustomers] = useState<Customer[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    const ac = new AbortController()

    setLoading(true)

    customersApi
      .list(ac.signal)
      .then(setCustomers)
      .catch((e) => {
        if ((e instanceof ApiError && e.kind === 'abort') ||
            (e instanceof DOMException && e.name === 'AbortError')) {
          return
        }

        setError(e instanceof Error ? e.message : 'Unknown error')
      })
      .finally(() => {
        setLoading(false)
      })

    return () => ac.abort()
  }, [])

  async function createCustomer(draft: CustomerDraft) {
    const customer = await customersApi.create(draft)

    setCustomers((prev) => [...prev, customer])

    return customer
  }

  async function updateCustomer(id:string, draft: CustomerDraft) {
    const customer = await customersApi.update(id, draft)

    setCustomers((prev) => 
        prev.map((customer) =>
          customer.customerId === id ? {...draft, customerId: id} : customer))
    return customer
  }

  return { customers, loading, error, createCustomer, updateCustomer }
}