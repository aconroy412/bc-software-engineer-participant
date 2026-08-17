import type { Customer } from '../types/customer'
import { StatusBadge } from './StatusBadge'

export function CustomerCard({
  customer,
  onEdit,
}: {
  customer: Customer
  onEdit: (customerId: string) => void
}) {
  
  // TODO: article with name, email, StatusBadge, Edit button calling onEdit(customer.customerId)
  return (
    <article className="card" data-testid={`card-${customer.customerId}`}>
      <p>{customer.fullName}</p>
      <p>{customer.email}</p>
      <StatusBadge status={customer.status} />
      <button onClick={() => onEdit(customer.customerId)}>Edit Customer</button>
    </article>
  )
}
