import type { Customer, CustomerDraft } from '../types/customer'
import { http } from './http'

type CustomerApiResponse = {
  id: string
  name: string
  email: string
  status: Customer['status']
}

export const customersApi = {
  async list(signal?: AbortSignal): Promise<Customer[]> {
    const data = await http<CustomerApiResponse[]>(
      '/api/customers',
      {},
      signal
    )

    return data.map((c) => ({
      customerId: c.id,
      fullName: c.name,
      email: c.email,
      status: c.status,
    }))
  },
  async get(customerId: string, signal?: AbortSignal): Promise<Customer> {
    // TODO: GET /api/customers/{id}
    const data = await http<CustomerApiResponse>(`/api/customers/${customerId}`, {}, signal)

    return {
      customerId: data.id,
      fullName: data.name,
      email: data.email,
      status: data.status,
    }
  },
  async create(draft: CustomerDraft, signal?: AbortSignal): Promise<Customer> {
    const data = await http<CustomerApiResponse>("/api/customers", {
      method: "POST",
      body: JSON.stringify(draft),}, 
      signal,)
    return {
      customerId: data.id,
      fullName: data.name,
      email: data.email,
      status: data.status,
    }
  },
  async update (id: string, draft: CustomerDraft, signal?: AbortSignal): Promise<Customer> {
    const data = await http<CustomerApiResponse>(`/api/customers/${encodeURIComponent(id)}`, {
      method: "PUT",
      body: JSON.stringify(draft),},
      signal,)
    return {
      customerId: data.id,
      fullName: data.name,
      email: data.email,
      status: data.status,
    }
  },
  async remove (id: string, signal?: AbortSignal) : Promise<Customer> {
    const data = await http<CustomerApiResponse>(`/api/customers/${encodeURIComponent(id)}`, {
      method: "DELETE",},
    signal,)
    return {
      customerId: data.id,
      fullName: data.name,
      email: data.email,
      status: data.status,
    }
  }
}
