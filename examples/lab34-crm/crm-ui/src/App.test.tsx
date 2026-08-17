import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import App from './App'

describe('App flows', () => {
  it('shows seed customers', () => {
    render(<App />)

    expect(screen.getByText(/Amina Khan/i)).toBeInTheDocument()
    expect(screen.getByText(/Ravi Singh/i)).toBeInTheDocument()
  })

  it('opens create form', async () => {
    const user = userEvent.setup()
    render(<App />)

    await user.click(
      screen.getByRole('button', { name: /new customer/i })
    )

    expect(screen.getByLabelText(/full name/i)).toBeInTheDocument()
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument()
    expect(screen.getByLabelText(/status/i)).toBeInTheDocument()
  })

  it('search amina leaves one card', async () => {
    const user = userEvent.setup()
    render(<App />)

    const searchInput = screen.getByRole('searchbox', {
      name: /search customers/i,
    })

    await user.type(searchInput, 'amina')

    expect(screen.getAllByRole('article')).toHaveLength(1)
  })

  it('create valid customer', async () => {
    const user = userEvent.setup()
    render(<App />)

    await user.click(
      screen.getByRole('button', { name: /new customer/i })
    )

    const nameInput = screen.getByLabelText(/full name/i)
    const emailInput = screen.getByLabelText(/email/i)
    const statusInput = screen.getByLabelText(/status/i)

    await user.type(nameInput, 'valid-name')
    await user.type(emailInput, 'validemail@valid.com')
    await user.selectOptions(statusInput, 'ACTIVE')

    await user.click(screen.getByRole('button', { name: /save/i }))

    expect(screen.getByText(/valid-name/i)).toBeInTheDocument()
  })

  it('invalid create shows errors and leaves list unchanged', async () => {
    const user = userEvent.setup()
    render(<App />)

    const initialCards = screen.getAllByRole('article')
    expect(initialCards).toHaveLength(2)

    await user.click(
      screen.getByRole('button', { name: /new customer/i })
    )

    await user.click(screen.getByRole('button', { name: /save/i }))

    expect(screen.getByText(/full name is required/i)).toBeInTheDocument()
    expect(screen.getByText(/email must be valid/i)).toBeInTheDocument()

    expect(screen.getAllByRole('article')).toHaveLength(2)
  })

  it('edit Ravi and save updated name', async () => {
    const user = userEvent.setup()
    render(<App />)

    const raviCard = screen.getByText(/Ravi Singh/i).closest('article')

    expect(raviCard).toBeInTheDocument()

    const editButton = raviCard!.querySelector('button')

    expect(editButton).toBeInTheDocument()

    await user.click(editButton!)

    const nameInput = screen.getByLabelText(/full name/i)

    await user.clear(nameInput)
    await user.type(nameInput, 'Ravi Updated')

    await user.click(
      screen.getByRole('button', { name: /save/i })
    )

    expect(screen.getByText(/Ravi Updated/i)).toBeInTheDocument()
    expect(screen.queryByText(/Ravi Singh/i)).not.toBeInTheDocument()

    expect(screen.getAllByRole('article')).toHaveLength(2)
  })

  it('cancel create does not add a customer', async () => {
    const user = userEvent.setup()
    render(<App />)

    expect(screen.getAllByRole('article')).toHaveLength(2)

    await user.click(
      screen.getByRole('button', { name: /new customer/i })
    )

    const nameInput = screen.getByLabelText(/full name/i)

    await user.type(nameInput, 'Should Not Exist')

    await user.click(
      screen.getByRole('button', { name: /cancel/i })
    )

    expect(screen.queryByText(/Should Not Exist/i)).not.toBeInTheDocument()
    expect(screen.getAllByRole('article')).toHaveLength(2)
  })

  it('search miss shows empty state', async () => {
    const user = userEvent.setup()
    render(<App />)

    const searchInput = screen.getByRole('searchbox', {
      name: /search customers/i,
    })

    await user.type(searchInput, 'this-does-not-exist')

    expect(screen.getByText(/No customers yet/)).toBeInTheDocument()
  })
})