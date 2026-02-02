'use client'

import ProtectedRoute from "@/components/auth/ProtectedRoutes";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { useAuth } from "@/hooks/use-auth";
import { Button } from "@/components/ui/button";

export default function DashboardLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  const pathname = usePathname();
  const { logout } = useAuth();

  const navLinks = [
    { href: '/editorial', label: 'Editoriales' },
    { href: '/libros', label: 'Libros' },
    { href: '/profesor', label: 'Profesores' },
    { href: '/curso', label: 'Cursos' },
    { href: '/colegio', label: 'Colegios' },
    { href: '/aula', label: 'Aulas' },
    { href: '/asignatura', label: 'Asignaturas' },
  ];

  const isActive = (path: string) => pathname === path;

  return (
    <ProtectedRoute>
      <div className="min-h-screen flex flex-col">
        <nav className="bg-white border-b border-gray-200 shadow-sm">
          <div className="container mx-auto px-4">
            <div className="flex items-center justify-between h-16">
              <div className="flex items-center space-x-8">
                <Link href="/editorial" className="text-xl font-bold text-gray-800">
                  Sistema Gestión
                </Link>
                <div className="hidden md:flex space-x-1">
                  {navLinks.map((link) => (
                    <Link
                      key={link.href}
                      href={link.href}
                      className={`px-3 py-2 rounded-md text-sm font-medium transition-colors ${
                        isActive(link.href)
                          ? 'bg-gray-900 text-white'
                          : 'text-gray-700 hover:bg-gray-100'
                      }`}
                    >
                      {link.label}
                    </Link>
                  ))}
                </div>
              </div>
              <Button
                variant="outline"
                size="sm"
                onClick={logout}
              >
                Cerrar Sesión
              </Button>
            </div>
          </div>
        </nav>
        <main className="flex-1 bg-gray-50">
          {children}
        </main>
      </div>
    </ProtectedRoute>
  );
}
