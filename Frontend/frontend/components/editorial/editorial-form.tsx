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
import { useCreateEditorial, useUpdateEditorial } from '@/hooks/use-editorial';
import { Editorial, editorialFormSchema, EditorialFormInput } from '@/schemas/editorial';

interface EditorialFormProps {
  editorial?: Editorial | null;
  onClose: () => void;
  isOpen: boolean;
}

export default function EditorialForm({ editorial, onClose, isOpen }: EditorialFormProps) {
  const createEditorial = useCreateEditorial();
  const updateEditorial = useUpdateEditorial();
  const [isLoading, setIsLoading] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});

  const [formData, setFormData] = useState<EditorialFormInput>({
    nombre: '',
  });

  useEffect(() => {
    if (editorial) {
      setFormData({
        nombre: editorial.nombre,
      });
    } else {
      setFormData({
        nombre: '',
      });
    }
    setErrors({});
  }, [editorial, isOpen]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsLoading(true);
    setErrors({});

    try {
      const validatedData = editorialFormSchema.parse(formData);

      if (editorial?.id) {
        await updateEditorial.mutateAsync({ id: editorial.id, data: validatedData });
      } else {
        await createEditorial.mutateAsync(validatedData);
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
          <DialogTitle>{editorial ? 'Editar Editorial' : 'Agregar Editorial'}</DialogTitle>
          <DialogDescription>
            {editorial
              ? 'Modifica los datos de la editorial'
              : 'Completa el formulario para agregar una nueva editorial'}
          </DialogDescription>
        </DialogHeader>

        <form onSubmit={handleSubmit}>
          <FieldGroup>
            <Field>
              <FieldLabel htmlFor="nombre">Nombre de la Editorial</FieldLabel>
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
              {isLoading ? 'Guardando...' : editorial ? 'Actualizar' : 'Crear'}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
