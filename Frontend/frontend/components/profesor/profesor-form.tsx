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
import { useCreateProfesor, useUpdateProfesor } from '@/hooks/use-profesor';
import { Profesor, profesorFormSchema, ProfesorFormInput } from '@/schemas/profesor';

interface ProfesorFormProps {
  profesor?: Profesor | null;
  onClose: () => void;
  isOpen: boolean;
}

export default function ProfesorForm({ profesor, onClose, isOpen }: ProfesorFormProps) {
  const createProfesor = useCreateProfesor();
  const updateProfesor = useUpdateProfesor();
  const [isLoading, setIsLoading] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});

  const [formData, setFormData] = useState<ProfesorFormInput>({
    nombre: '',
  });

  useEffect(() => {
    if (profesor) {
      setFormData({
        nombre: profesor.nombre,
      });
    } else {
      setFormData({
        nombre: '',
      });
    }
    setErrors({});
  }, [profesor, isOpen]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsLoading(true);
    setErrors({});

    try {
      const validatedData = profesorFormSchema.parse(formData);

      if (profesor?.id) {
        await updateProfesor.mutateAsync({ id: profesor.id, data: validatedData });
      } else {
        await createProfesor.mutateAsync(validatedData);
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
          <DialogTitle>{profesor ? 'Editar Profesor' : 'Agregar Profesor'}</DialogTitle>
          <DialogDescription>
            {profesor
              ? 'Modifica los datos del profesor'
              : 'Completa el formulario para agregar un nuevo profesor'}
          </DialogDescription>
        </DialogHeader>

        <form onSubmit={handleSubmit}>
          <FieldGroup>
            <Field>
              <FieldLabel htmlFor="nombre">Nombre del Profesor</FieldLabel>
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
              {isLoading ? 'Guardando...' : profesor ? 'Actualizar' : 'Crear'}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
