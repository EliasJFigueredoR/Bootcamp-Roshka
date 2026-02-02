'use client'

import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { useProfesores, useDeleteProfesor } from '@/hooks/use-profesor';
import { Profesor } from '@/schemas/profesor';
import ProfesorForm from './profesor-form';

export default function ProfesorTable() {
  const { data: profesores, isLoading, error } = useProfesores();
  const deleteProfesor = useDeleteProfesor();
  const [editingProfesor, setEditingProfesor] = useState<Profesor | null>(null);
  const [isFormOpen, setIsFormOpen] = useState(false);

  const handleEdit = (profesor: Profesor) => {
    setEditingProfesor(profesor);
    setIsFormOpen(true);
  };

  const handleDelete = (id: number) => {
    if (confirm('¿Estás seguro de que deseas eliminar este profesor?')) {
      deleteProfesor.mutate(id);
    }
  };

  const handleFormClose = () => {
    setIsFormOpen(false);
    setEditingProfesor(null);
  };

  if (isLoading) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center">Cargando profesores...</p>
        </CardContent>
      </Card>
    );
  }

  if (error) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center text-red-500">Error al cargar profesores: {error.message}</p>
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
              <CardTitle>Profesores</CardTitle>
              <CardDescription>Gestiona los profesores del sistema</CardDescription>
            </div>
            <Button onClick={() => setIsFormOpen(true)}>Agregar Profesor</Button>
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
                {profesores && profesores.length > 0 ? (
                  profesores.map((profesor) => (
                    <tr key={profesor.id} className="border-b hover:bg-gray-50">
                      <td className="p-4">{profesor.id}</td>
                      <td className="p-4">{profesor.nombre}</td>
                      <td className="p-4 space-x-2">
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleEdit(profesor)}
                        >
                          Editar
                        </Button>
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleDelete(profesor.id!)}
                          disabled={deleteProfesor.isPending}
                        >
                          Eliminar
                        </Button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={3} className="p-4 text-center text-gray-500">
                      No hay profesores registrados
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>

      {isFormOpen && (
        <ProfesorForm
          profesor={editingProfesor}
          onClose={handleFormClose}
          isOpen={isFormOpen}
        />
      )}
    </div>
  );
}
