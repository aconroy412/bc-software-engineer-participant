import { ApiError } from "./ApiError"

const baseUrl = import.meta.env.VITE_API_BASE_URL as string

export async function http<T>(
  path: string,
  init: RequestInit = {},
  signal?: AbortSignal,
): Promise<T> {
  try {
    const response = await fetch(`${baseUrl}${path}`, {
      ...init,
      headers: {
        "Content-Type": "application/json",
        "X-Correlation-Id": "lab-request-001",
        ...init.headers,
      },
      signal,
    })

    if (response.status === 204) {
      return undefined as T
    }

    if (!response.ok) {
      throw await ApiError.from(response)
    }

    return response.json() as Promise<T>
  } catch (e) {
    if (e instanceof ApiError) {
      throw e
    }

    if (e instanceof DOMException && e.name === 'AbortError') {
      throw new ApiError(
        'Request aborted',
        'abort',
      )
    }

    if (e instanceof Error) {
      throw new ApiError(
        e.message,
        'network',
      )
    }

    throw new ApiError(
      'Unknown network error',
      'network',
    )
  }
}