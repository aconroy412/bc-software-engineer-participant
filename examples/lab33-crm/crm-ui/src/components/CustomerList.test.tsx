import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import { CustomerList } from './CustomerList'
import { seedCustomers } from '../data/seedCustomers'

describe('CustomerList', () => {
  it('renders fixture customers by name', () => {
    render(<CustomerList customers={seedCustomers} onEdit={() => {}} />)
    // TODO: assert Amina / Ravi visible (getByText or getByRole)

    expect(screen.getByText(/Amina Khan/i)).toBeInTheDocument()
    expect(screen.getByText(/Ravi Singh/i)).toBeInTheDocument()
  })

  it("reports the selected customer", async () => {
    const user = userEvent.setup();
    const onEdit = vi.fn();
    const amina = seedCustomers[0]
    render(<CustomerList customers={[amina]} onEdit={onEdit} />);
    await user.click(screen.getByRole("button", { name: "Edit Customer" }));
    expect(onEdit).toHaveBeenCalledWith("CUS-1001");
  });

  it("Empty State when customer=[{}]", async () => {
    render(<CustomerList customers={[]} onEdit={() => {}} />)

    expect(screen.getByText(/No customers yet/i)).toBeInTheDocument
  });
})
