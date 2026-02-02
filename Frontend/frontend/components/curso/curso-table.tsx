'use client'

import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { useCursos, useDeleteCurso } from '@/hooks/use-curso';
import { Curso } from '@/schemas/curso';
import CursoForm from './curso-form';

export default function CursoTable() {
  const { data: cursos, isLoading, error } = useCursos();
  const deleteCurso = useDeleteCurso();
  const [editingCurso, setEditingCurso] = useState<Curso | null>(null);
  const [isFormOpen, setIsFormOpen] = useState(false);

  const handleEdit = (curso: Curso) => {
    setEditingCurso(curso);
    setIsFormOpen(true);
  };

  const handleDelete = (id: number) => {
    if (confirm('¿Estás seguro de que deseas eliminar este curso?')) {
      deleteCurso.mutate(id);
    }
  };

  const handleFormClose = () => {
    setIsFormOpen(false);
    setEditingCurso(null);
  };

  if (isLoading) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center">Cargando cursos...</p>
        </CardContent>
      </Card>
    );
  }

  if (error) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center text-red-500">Error al cargar cursos: {error.message}</p>
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
              <CardTitle>Cursos</CardTitle>
              <CardDescription>Gestiona los cursos del sistema</CardDescription>
            </div>
            <Button onClick={() => setIsFormOpen(true)}>Agregar Curso</Button>
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
                {cursos && cursos.length > 0 ? (
                  cursos.map((curso) => (
                    <tr key={curso.id} className="border-b hover:bg-gray-50">
                      <td className="p-4">{curso.id}</td>
                      <td className="p-4">{curso.nombre}</td>
                      <td className="p-4 space-x-2">
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleEdit(curso)}
                        >
                          Editar
                        </Button>
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleDelete(curso.id!)}
                          disabled={deleteCurso.isPending}
                        >
                          Eliminar
                        </Button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={3} className="p-4 text-center text-gray-500">
                      No hay cursos registrados
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>

      {isFormOpen && (
        <CursoForm
          curso={editingCurso}
          onClose={handleFormClose}
          isOpen={isFormOpen}
        />
      )}
    </div>
  );
}
