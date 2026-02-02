'use client'

import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { useLibros, useDeleteLibro } from '@/hooks/use-libros';
import { Libro } from '@/schemas/libros';
import LibrosForm from './libros-form';

export default function LibrosTable() {
  const { data: libros, isLoading, error } = useLibros();
  const deleteLibro = useDeleteLibro();
  const [editingLibro, setEditingLibro] = useState<Libro | null>(null);
  const [isFormOpen, setIsFormOpen] = useState(false);

  const handleEdit = (libro: Libro) => {
    setEditingLibro(libro);
    setIsFormOpen(true);
  };

  const handleDelete = (id: number) => {
    if (confirm('¿Estás seguro de que deseas eliminar este libro?')) {
      deleteLibro.mutate(id);
    }
  };

  const handleFormClose = () => {
    setIsFormOpen(false);
    setEditingLibro(null);
  };

  if (isLoading) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center">Cargando libros...</p>
        </CardContent>
      </Card>
    );
  }

  if (error) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center text-red-500">Error al cargar libros: {error.message}</p>
        </CardContent>
      </Card>
    );
  }

  return (
    <div className="space-y-4">
      <Card>
        <CardHeader>
          <div className="flex justify-between items-center">
            <div>
              <CardTitle>Libros</CardTitle>
              <CardDescription>Gestiona los libros del sistema</CardDescription>
            </div>
            <Button onClick={() => setIsFormOpen(true)}>Agregar Libro</Button>
          </div>
        </CardHeader>
        <CardContent>
          <div className="overflow-x-auto">
            <table className="w-full border-collapse">
              <thead>
                <tr className="border-b">
                  <th className="text-left p-4">ID</th>
                  <th className="text-left p-4">Nombre</th>
                  <th className="text-left p-4">Cantidad</th>
                  <th className="text-left p-4">Acciones</th>
                </tr>
              </thead>
              <tbody>
                {libros && libros.length > 0 ? (
                  libros.map((libro) => (
                    <tr key={libro.id} className="border-b hover:bg-gray-50">
                      <td className="p-4">{libro.id}</td>
                      <td className="p-4">{libro.nombre}</td>
                      <td className="p-4">{libro.cantidad}</td>
                      <td className="p-4 space-x-2">
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleEdit(libro)}
                        >
                          Editar
                        </Button>
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleDelete(libro.id!)}
                          disabled={deleteLibro.isPending}
                        >
                          Eliminar
                        </Button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={4} className="p-4 text-center text-gray-500">
                      No hay libros registrados
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>

      {isFormOpen && (
        <LibrosForm
          libro={editingLibro}
          onClose={handleFormClose}
          isOpen={isFormOpen}
        />
      )}
    </div>
  );
}
