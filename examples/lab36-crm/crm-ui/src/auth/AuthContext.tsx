import { createContext, useContext, useEffect, useMemo, useState, type ReactNode } from 'react'
import { tokenStore } from './tokenStore'
import { http } from '../api/http'

type User = {
  id: string
  // ...whatever fields you need
}

type AuthState =
  | { status: 'checking' }
  | { status: 'anonymous' }
  | { status: 'authenticated'; user: User }

type AuthContextValue = {
  status: AuthState['status']
  user: User | null
  login: (token: string, user: User) => void
  logout: () => void
}

const AuthContext = createContext<AuthContextValue | null>(null)

export function AuthProvider({ children }: { children: ReactNode }) {
  const [state, setState] = useState<AuthState>({ status: 'checking' })

  useEffect(() => {
    const token = tokenStore.get()
    if (!token) {
      setState({ status: 'anonymous' })
      return
    }
    // TODO: validate token with server, e.g. fetch current user
    // On success: setState({ status: 'authenticated', user })
    // On failure: tokenStore.clear(); setState({ status: 'anonymous' })

    try {
      const response = await http()
    }
  }, [])

  const value = useMemo<AuthContextValue>(
    () => ({
      status: state.status,
      user: state.status === 'authenticated' ? state.user : null,
      login: (token: string, user: User) => {
        tokenStore.set(token)
        setState({ status: 'authenticated', user })
      },
      logout: () => {
        tokenStore.clear()
        setState({ status: 'anonymous' })
      },
    }),
    [state],
  )

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
  const ctx = useContext(AuthContext)
  if (!ctx) throw new Error('useAuth requires AuthProvider')
  return ctx
}