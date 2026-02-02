'use client'

import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { useAsignaturas, useDeleteAsignatura } from '@/hooks/use-asignatura';
import { Asignatura } from '@/schemas/asignatura';
import AsignaturaForm from './asignatura-form';

export default function AsignaturaTable() {
  const { data: asignaturas, isLoading, error } = useAsignaturas();
  const deleteAsignatura = useDeleteAsignatura();
  const [editingAsignatura, setEditingAsignatura] = useState<Asignatura | null>(null);
  const [isFormOpen, setIsFormOpen] = useState(false);

  const handleEdit = (asignatura: Asignatura) => {
    setEditingAsignatura(asignatura);
    setIsFormOpen(true);
  };

  const handleDelete = (id: number) => {
    if (confirm('¿Estás seguro de que deseas eliminar esta asignatura?')) {
      deleteAsignatura.mutate(id);
    }
  };

  const handleFormClose = () => {
    setIsFormOpen(false);
    setEditingAsignatura(null);
  };

  if (isLoading) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center">Cargando asignaturas...</p>
        </CardContent>
      </Card>
    );
  }

  if (error) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center text-red-500">Error al cargar asignaturas: {error.message}</p>
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
              <CardTitle>Asignaturas</CardTitle>
              <CardDescription>Gestiona las asignaturas del sistema</CardDescription>
            </div>
            <Button onClick={() => setIsFormOpen(true)}>Agregar Asignatura</Button>
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
                {asignaturas && asignaturas.length > 0 ? (
                  asignaturas.map((asignatura) => (
                    <tr key={asignatura.id} className="border-b hover:bg-gray-50">
                      <td className="p-4">{asignatura.id}</td>
                      <td className="p-4">{asignatura.nombre}</td>
                      <td className="p-4 space-x-2">
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleEdit(asignatura)}
                        >
                          Editar
                        </Button>
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleDelete(asignatura.id!)}
                          disabled={deleteAsignatura.isPending}
                        >
                          Eliminar
                        </Button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={3} className="p-4 text-center text-gray-500">
                      No hay asignaturas registradas
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>

      {isFormOpen && (
        <AsignaturaForm
          asignatura={editingAsignatura}
          onClose={handleFormClose}
          isOpen={isFormOpen}
        />
      )}
    </div>
  );
}
