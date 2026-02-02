'use client'

import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { useEditoriales, useDeleteEditorial } from '@/hooks/use-editorial';
import { Editorial } from '@/schemas/editorial';
import EditorialForm from './editorial-form';

export default function EditorialTable() {
  const { data: editoriales, isLoading, error } = useEditoriales();
  const deleteEditorial = useDeleteEditorial();
  const [editingEditorial, setEditingEditorial] = useState<Editorial | null>(null);
  const [isFormOpen, setIsFormOpen] = useState(false);

  const handleEdit = (editorial: Editorial) => {
    setEditingEditorial(editorial);
    setIsFormOpen(true);
  };

  const handleDelete = (id: number) => {
    if (confirm('¿Estás seguro de que deseas eliminar esta editorial?')) {
      deleteEditorial.mutate(id);
    }
  };

  const handleFormClose = () => {
    setIsFormOpen(false);
    setEditingEditorial(null);
  };

  if (isLoading) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center">Cargando editoriales...</p>
        </CardContent>
      </Card>
    );
  }

  if (error) {
    return (
      <Card>
        <CardContent className="pt-6">
          <p className="text-center text-red-500">Error al cargar editoriales: {error.message}</p>
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
              <CardTitle>Editoriales</CardTitle>
              <CardDescription>Gestiona las editoriales del sistema</CardDescription>
            </div>
            <Button onClick={() => setIsFormOpen(true)}>Agregar Editorial</Button>
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
                {editoriales && editoriales.length > 0 ? (
                  editoriales.map((editorial) => (
                    <tr key={editorial.id} className="border-b hover:bg-gray-50">
                      <td className="p-4">{editorial.id}</td>
                      <td className="p-4">{editorial.nombre}</td>
                      <td className="p-4 space-x-2">
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleEdit(editorial)}
                        >
                          Editar
                        </Button>
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => handleDelete(editorial.id!)}
                          disabled={deleteEditorial.isPending}
                        >
                          Eliminar
                        </Button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={3} className="p-4 text-center text-gray-500">
                      No hay editoriales registradas
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>

      {isFormOpen && (
        <EditorialForm
          editorial={editingEditorial}
          onClose={handleFormClose}
          isOpen={isFormOpen}
        />
      )}
    </div>
  );
}
