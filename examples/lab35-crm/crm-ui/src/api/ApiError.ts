export type ApiErrorKind = 'network' | 'http' | 'abort' | 'parse'

export class ApiError extends Error {
  constructor(
    message: string,
    public readonly kind: ApiErrorKind,
    public readonly status?: number,
    public readonly fieldErrors?: Record<string, string>,
  ) {
    super(message)
    this.name = 'ApiError'
  }

  static async from(response: Response): Promise<ApiError> {
    let message = 'Request failed'
    let fieldErrors: Record<string, string> | undefined

    try {
      const body = await response.json()

      if (typeof body?.message === 'string') {
        message = body.message
      }

      if (body?.fieldErrors && typeof body.fieldErrors === 'object') {
        fieldErrors = body.fieldErrors
      }
    } catch {
      // Keep safe defaults if response isn't JSON.
    }

    return new ApiError(
      message,
      'http',
      response.status,
      fieldErrors,
    )
  }
}