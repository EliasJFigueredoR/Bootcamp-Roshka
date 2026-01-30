'use client'

import { PropsWithChildren } from 'react'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'

const queryClient: QueryClient = new QueryClient({
  defaultOptions: {
    queries: {
      // ejemplos de opciones globales
      gcTime: 1000 * 60 * 5,   
      staleTime: 1000 * 60,       
      retry: 1,                  
      refetchOnWindowFocus: false,
    },
  },
})

export default function QueryProvider({ children }: PropsWithChildren) {
  return (
    <QueryClientProvider client={queryClient}>
      {children}
      {process.env.NODE_ENV === 'development' && (
        <ReactQueryDevtools initialIsOpen={false} />
      )}
    </QueryClientProvider>
  )
}