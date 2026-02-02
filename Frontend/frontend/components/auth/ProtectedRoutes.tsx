'use client'

import { useAuth } from '@/hooks/use-auth';
import { useRouter } from 'next/navigation';
import { useEffect } from 'react';

export default function ProtectedRoute({ children }: { children: React.ReactNode }) {
  const { isAuthenticated, isLoading } = useAuth();
  const router= useRouter();

  useEffect(() => {
    // Si terminó de cargar y no está autenticado, lo mandamos al login
    if (!isLoading && !isAuthenticated) {
      router.push('/login');
    }
  }, [isLoading, isAuthenticated, router]);

  // Mientras verificamos el token con, mostramos una pantalla de carga
  if (isLoading) {
    return (
      <div className="flex h-screen items-center justify-center">
        <p>Cargando sesión...</p> {/* Aquí podrías poner un Spinner de ShadCN */}
      </div>
    );
  }

  // Si no está autenticado, no renderizamos nada (el useEffect hará la redirección)
  if (!isAuthenticated) {
    return null;
  }

  // Si todo está bien, mostramos la página protegida
  return <>{children}</>;
}