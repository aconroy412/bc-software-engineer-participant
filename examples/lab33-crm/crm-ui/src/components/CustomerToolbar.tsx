

export function CustomerToolbar({ onAdd } : {onAdd: () => void}) 
{
  return (
    <div>
      <h2>Customers</h2>

      <button onClick={onAdd}>
        Add Customer
      </button>
    </div>
  )
}