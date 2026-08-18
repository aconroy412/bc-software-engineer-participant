import { customersApi } from './customers'
import { ApiError } from './ApiError'

describe('customersApi', () => {
  beforeEach(() => {
    vi.restoreAllMocks()
  })

  it('handles 200 list with Amina and Ravi', async () => {
    vi.spyOn(globalThis, 'fetch').mockResolvedValue(
      new Response(
        JSON.stringify([
          {
            id: 'CUS-1001',
            name: 'Amina Khan',
            email: 'amina.khan@example.com',
            status: 'ACTIVE',
          },
          {
            id: 'CUS-1002',
            name: 'Ravi Singh',
            email: 'ravi.singh@example.com',
            status: 'PROSPECT',
          },
        ]),
        {
          status: 200,
          headers: {
            'Content-Type': 'application/json',
          },
        },
      ),
    )

    const customers = await customersApi.list()

    expect(customers).toEqual([
      {
        customerId: 'CUS-1001',
        fullName: 'Amina Khan',
        email: 'amina.khan@example.com',
        status: 'ACTIVE',
      },
      {
        customerId: 'CUS-1002',
        fullName: 'Ravi Singh',
        email: 'ravi.singh@example.com',
        status: 'PROSPECT',
      },
    ])
  })

  it('handles 201 create', async () => {
    vi.spyOn(globalThis, 'fetch').mockResolvedValue(
      new Response(
        JSON.stringify({
          id: 'CUS-1003',
          name: 'John Smith',
          email: 'john@example.com',
          status: 'PROSPECT',
        }),
        {
          status: 201,
          headers: {
            'Content-Type': 'application/json',
          },
        },
      ),
    )

    const created = await customersApi.create({
      fullName: 'John Smith',
      email: 'john@example.com',
      status: 'PROSPECT',
    })

    expect(created).toEqual({
      customerId: 'CUS-1003',
      fullName: 'John Smith',
      email: 'john@example.com',
      status: 'PROSPECT',
    })
  })

  it('handles 400 field errors', async () => {
    vi.spyOn(globalThis, 'fetch').mockResolvedValue(
      new Response(
        JSON.stringify({
          message: 'Validation failed',
          fieldErrors: {
            email: 'Email must be valid',
          },
        }),
        {
          status: 400,
          headers: {
            'Content-Type': 'application/json',
          },
        },
      ),
    )

    await expect(
      customersApi.create({
        fullName: 'John Smith',
        email: 'bad-email',
        status: 'PROSPECT',
      }),
    ).rejects.toMatchObject({
      kind: 'http',
      status: 400,
      fieldErrors: {
        email: 'Email must be valid',
      },
    })
  })

  it('handles 500 server message as ApiError', async () => {
    vi.spyOn(globalThis, 'fetch').mockResolvedValue(
      new Response(
        JSON.stringify({
          message: 'Database unavailable',
        }),
        {
          status: 500,
          headers: {
            'Content-Type': 'application/json',
          },
        },
      ),
    )

    await expect(
      customersApi.list(),
    ).rejects.toMatchObject({
      kind: 'http',
      status: 500,
      message: 'Database unavailable',
    })

    try {
      await customersApi.list()
    } catch (e) {
      expect(e).toBeInstanceOf(ApiError)
    }
  })

  it('handles network failure', async () => {
    vi.spyOn(globalThis, 'fetch').mockRejectedValue(
      new Error('Network failure'),
    )

    await expect(
      customersApi.list(),
    ).rejects.toThrow('Network failure')
  })

  it('handles abort', async () => {
    const controller = new AbortController()

    vi.spyOn(globalThis, 'fetch').mockRejectedValue(
      new DOMException('The operation was aborted.', 'AbortError'),
    )

    const promise = customersApi.list(controller.signal)

    controller.abort()

    await expect(promise).rejects.toMatchObject({
      kind: 'abort',
    })
  })
})