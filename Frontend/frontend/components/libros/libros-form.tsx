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
import { useCreateLibro, useUpdateLibro } from '@/hooks/use-libros';
import { Libro, libroFormSchema, LibroFormInput } from '@/schemas/libros';

interface LibrosFormProps {
  libro?: Libro | null;
  onClose: () => void;
  isOpen: boolean;
}

export default function LibrosForm({ libro, onClose, isOpen }: LibrosFormProps) {
  const createLibro = useCreateLibro();
  const updateLibro = useUpdateLibro();
  const [isLoading, setIsLoading] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});

  const [formData, setFormData] = useState<LibroFormInput>({
    nombre: '',
    cantidad: 0,
  });

  useEffect(() => {
    if (libro) {
      setFormData({
        nombre: libro.nombre,
        cantidad: libro.cantidad,
      });
    } else {
      setFormData({
        nombre: '',
        cantidad: 0,
      });
    }
    setErrors({});
  }, [libro, isOpen]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsLoading(true);
    setErrors({});

    try {
      const validatedData = libroFormSchema.parse(formData);

      if (libro?.id) {
        await updateLibro.mutateAsync({ id: libro.id, data: validatedData });
      } else {
        await createLibro.mutateAsync(validatedData);
      }

      onClose();
    } catch (error) {
      console.log(error)
    } finally {
      setIsLoading(false);
    }
  };

  const handleInputChange = (field: keyof LibroFormInput, value: string | number) => {
    setFormData((prev) => ({ ...prev, [field]: value }));
    if (errors[field]) {
      setErrors((prev) => {
        const newErrors = { ...prev };
        delete newErrors[field];
        return newErrors;
      });
    }
  };

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>{libro ? 'Editar Libro' : 'Agregar Libro'}</DialogTitle>
          <DialogDescription>
            {libro
              ? 'Modifica los datos del libro'
              : 'Completa el formulario para agregar un nuevo libro'}
          </DialogDescription>
        </DialogHeader>

        <form onSubmit={handleSubmit}>
          <FieldGroup>
            <Field>
              <FieldLabel htmlFor="nombre">Nombre del Libro</FieldLabel>
              <Input
                id="nombre"
                name="nombre"
                type="text"
                value={formData.nombre}
                onChange={(e) => handleInputChange('nombre', e.target.value)}
                required
              />
              {errors.nombre && (
                <p className="text-sm text-red-500 mt-1">{errors.nombre}</p>
              )}
            </Field>

            <Field>
              <FieldLabel htmlFor="cantidad">Cantidad</FieldLabel>
              <Input
                id="cantidad"
                name="cantidad"
                type="number"
                min="0"
                value={formData.cantidad}
                onChange={(e) => handleInputChange('cantidad', parseInt(e.target.value) || 0)}
                required
              />
              {errors.cantidad && (
                <p className="text-sm text-red-500 mt-1">{errors.cantidad}</p>
              )}
            </Field>
          </FieldGroup>

          <DialogFooter className="mt-4">
            <Button type="button" variant="outline" onClick={onClose}>
              Cancelar
            </Button>
            <Button type="submit" disabled={isLoading}>
              {isLoading ? 'Guardando...' : libro ? 'Actualizar' : 'Crear'}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
