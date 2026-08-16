import { useState } from 'react'
import { CustomerList } from './components/CustomerList'
import { seedCustomers } from './data/seedCustomers'
import { AppLayout } from './components/AppLayout'
import { CustomerToolbar } from './components/CustomerToolbar'
import { CustomerForm } from './components/CustomerForm'
import { CustomerStatus } from './types/customer'
import { LoadingState } from './components/LoadingState'
import { ErrorState } from './components/ErrorState'

export default function App() {
  const [customers] = useState(seedCustomers)
  const handleEdit = (customerId : string) => { 
    console.log(customerId)
  }
  const emptyDraft = {
    fullName: '',
    email: '',
    status: 'PROSPECT' as CustomerStatus,
  }
  const isLoading = false
  const isError = false

  return (
    <AppLayout>
      <CustomerToolbar onAdd={() => console.log("add", "lab-request-001")} />
      {isLoading ? (
        <LoadingState />
      ) : isError ? (
        <ErrorState
          message="Unable to load customers."
        />
      ) : (
        <CustomerList
          customers={seedCustomers}
          onEdit={(id) => console.log('edit', id, 'lab-request-001')}
        />
      )}
      <CustomerForm
        draft={emptyDraft}
        errors={{}}
        onChange={() => {}}
        onSubmit={() => {}}
        onCancel={() => {}}
      />
    </AppLayout>
  )
}
