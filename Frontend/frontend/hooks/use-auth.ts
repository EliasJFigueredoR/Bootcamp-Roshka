'use client'

import { useContext } from 'react';
import { AuthContext } from '@/context/auth-provider';
import type { AuthContextValue } from '@/types/auth';

/**
 * Hook personalizado para acceder al contexto de autenticación
 */
export function useAuth(): AuthContextValue {
  const context: AuthContextValue | undefined = useContext(AuthContext);
  
  if (context === undefined) {
    throw new Error(
      'useAuth debe usarse dentro de un AuthProvider. ' +
      'Asegúrate de envolver tu app con <AuthProvider>.'
    );
  }
  return context;
}

export default useAuth;