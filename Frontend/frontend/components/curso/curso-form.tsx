'use client'

import { useEffect, useState } from 'react';
import { Button } from '@/components/ui/button';
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog';
import { Field, FieldGroup, FieldLabel } from '@/components/ui/field';
import { Input } from '@/components/ui/input';
import { useCreateCurso, useUpdateCurso } from '@/hooks/use-curso';
import { Curso, cursoFormSchema, CursoFormInput } from '@/schemas/curso';

interface CursoFormProps {
  curso?: Curso | null;
  onClose: () => void;
  isOpen: boolean;
}

export default function CursoForm({ curso, onClose, isOpen }: CursoFormProps) {
  const createCurso = useCreateCurso();
  const updateCurso = useUpdateCurso();
  const [isLoading, setIsLoading] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});

  const [formData, setFormData] = useState<CursoFormInput>({
    nombre: '',
  });

  useEffect(() => {
    if (curso) {
      setFormData({
        nombre: curso.nombre,
      });
    } else {
      setFormData({
        nombre: '',
      });
    }
    setErrors({});
  }, [curso, isOpen]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsLoading(true);
    setErrors({});

    try {
      const validatedData = cursoFormSchema.parse(formData);

      if (curso?.id) {
        await updateCurso.mutateAsync({ id: curso.id, data: validatedData });
      } else {
        await createCurso.mutateAsync(validatedData);
      }

      onClose();
    } catch (error) {
      console.log(error)
    } finally {
      setIsLoading(false);
    }
  };

  const handleInputChange = (value: string) => {
    setFormData({ nombre: value });
    if (errors.nombre) {
      setErrors({});
    }
  };

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>{curso ? 'Editar Curso' : 'Agregar Curso'}</DialogTitle>
          <DialogDescription>
            {curso
              ? 'Modifica los datos del curso'
              : 'Completa el formulario para agregar un nuevo curso'}
          </DialogDescription>
        </DialogHeader>

        <form onSubmit={handleSubmit}>
          <FieldGroup>
            <Field>
              <FieldLabel htmlFor="nombre">Nombre del Curso</FieldLabel>
              <Input
                id="nombre"
                name="nombre"
                type="text"
                value={formData.nombre}
                onChange={(e) => handleInputChange(e.target.value)}
                required
              />
              {errors.nombre && (
                <p className="text-sm text-red-500 mt-1">{errors.nombre}</p>
              )}
            </Field>
          </FieldGroup>

          <DialogFooter className="mt-4">
            <Button type="button" variant="outline" onClick={onClose}>
              Cancelar
            </Button>
            <Button type="submit" disabled={isLoading}>
              {isLoading ? 'Guardando...' : curso ? 'Actualizar' : 'Crear'}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
