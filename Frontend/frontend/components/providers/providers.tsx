'use client'

import { ReactNode } from 'react'
import QueryProvider from './queryProvider'
import { AuthProvider } from '@/context/auth-provider'

export default function Providers({ children }: { children: ReactNode }) {
  return (
    <AuthProvider>
      <QueryProvider>
        {children}
      </QueryProvider>
    </AuthProvider>
  )
}
