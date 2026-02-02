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
import { useCreateAsignatura, useUpdateAsignatura } from '@/hooks/use-asignatura';
import { Asignatura, asignaturaFormSchema, AsignaturaFormInput } from '@/schemas/asignatura';

interface AsignaturaFormProps {
  asignatura?: Asignatura | null;
  onClose: () => void;
  isOpen: boolean;
}

export default function AsignaturaForm({ asignatura, onClose, isOpen }: AsignaturaFormProps) {
  const createAsignatura = useCreateAsignatura();
  const updateAsignatura = useUpdateAsignatura();
  const [isLoading, setIsLoading] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});

  const [formData, setFormData] = useState<AsignaturaFormInput>({
    nombre: '',
  });

  useEffect(() => {
    if (asignatura) {
      setFormData({
        nombre: asignatura.nombre,
      });
    } else {
      setFormData({
        nombre: '',
      });
    }
    setErrors({});
  }, [asignatura, isOpen]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsLoading(true);
    setErrors({});

    try {
      const validatedData = asignaturaFormSchema.parse(formData);

      if (asignatura?.id) {
        await updateAsignatura.mutateAsync({ id: asignatura.id, data: validatedData });
      } else {
        await createAsignatura.mutateAsync(validatedData);
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
          <DialogTitle>{asignatura ? 'Editar Asignatura' : 'Agregar Asignatura'}</DialogTitle>
          <DialogDescription>
            {asignatura
              ? 'Modifica los datos de la asignatura'
              : 'Completa el formulario para agregar una nueva asignatura'}
          </DialogDescription>
        </DialogHeader>

        <form onSubmit={handleSubmit}>
          <FieldGroup>
            <Field>
              <FieldLabel htmlFor="nombre">Nombre de la Asignatura</FieldLabel>
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
              {isLoading ? 'Guardando...' : asignatura ? 'Actualizar' : 'Crear'}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
