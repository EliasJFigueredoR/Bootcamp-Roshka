'use client'

import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { useColegios, useDeleteColegio } from '@/hooks/use-colegio';
import { Colegio } from '@/schemas/colegio';
import ColegioForm from './colegio-form';

export default function ColegioTable() {
  const { data: colegios, isLoading, error } = useColegios();
  const deleteColegio = useDeleteColegio();
  const [editingColegio, setEditingColegio] = useState<Colegio | null>(null);
  const [isFormOpen, setIsFormOpen] = useState(false);

  const handleEdit = (colegio: Colegio) => {
    setEditingColegio(colegio);
    setIsFormOpen(true);
  };

  const handleDelete = (id: number) => {
    if (confirm('¿Estás seguro de que deseas eliminar este colegio?')) {
      deleteColegio.mutate(id);
    }
  };

  const handleFormClose = () => {
    setIsFormOpen(false);
    setEditingColegio(null);
  };

  if (isLoading) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center">Cargando colegios...</p>
        </CardContent>
      </Card>
    );
  }

  if (error) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center text-red-500">Error al cargar colegios: {error.message}</p>
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
              <CardTitle>Colegios</CardTitle>
              <CardDescription>Gestiona los colegios del sistema</CardDescription>
            </div>
            <Button onClick={() => setIsFormOpen(true)}>Agregar Colegio</Button>
          </div>
        </CardHeader>
        <CardContent>
          <div className="overflow-x-auto">
            <table className="w-full border-collapse">
              <thead>
                <tr className="border-b">
                  <th className="text-left p-4">ID</th>
                  <th className="text-left p-4">Nombre</th>
                  <th className="text-left p-4">Acciones</th>
                </tr>
              </thead>
              <tbody>
                {colegios && colegios.length > 0 ? (
                  colegios.map((colegio) => (
                    <tr key={colegio.id} className="border-b hover:bg-gray-50">
                      <td className="p-4">{colegio.id}</td>
                      <td className="p-4">{colegio.nombre}</td>
                      <td className="p-4 space-x-2">
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleEdit(colegio)}
                        >
                          Editar
                        </Button>
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleDelete(colegio.id!)}
                          disabled={deleteColegio.isPending}
                        >
                          Eliminar
                        </Button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={3} className="p-4 text-center text-gray-500">
                      No hay colegios registrados
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>

      {isFormOpen && (
        <ColegioForm
          colegio={editingColegio}
          onClose={handleFormClose}
          isOpen={isFormOpen}
        />
      )}
    </div>
  );
}
