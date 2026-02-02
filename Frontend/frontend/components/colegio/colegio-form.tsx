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
import { useCreateColegio, useUpdateColegio } from '@/hooks/use-colegio';
import { Colegio, colegioFormSchema, ColegioFormInput } from '@/schemas/colegio';

interface ColegioFormProps {
  colegio?: Colegio | null;
  onClose: () => void;
  isOpen: boolean;
}

export default function ColegioForm({ colegio, onClose, isOpen }: ColegioFormProps) {
  const createColegio = useCreateColegio();
  const updateColegio = useUpdateColegio();
  const [isLoading, setIsLoading] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});

  const [formData, setFormData] = useState<ColegioFormInput>({
    nombre: '',
  });

  useEffect(() => {
    if (colegio) {
      setFormData({
        nombre: colegio.nombre,
      });
    } else {
      setFormData({
        nombre: '',
      });
    }
    setErrors({});
  }, [colegio, isOpen]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsLoading(true);
    setErrors({});

    try {
      const validatedData = colegioFormSchema.parse(formData);

      if (colegio?.id) {
        await updateColegio.mutateAsync({ id: colegio.id, data: validatedData });
      } else {
        await createColegio.mutateAsync(validatedData);
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
          <DialogTitle>{colegio ? 'Editar Colegio' : 'Agregar Colegio'}</DialogTitle>
          <DialogDescription>
            {colegio
              ? 'Modifica los datos del colegio'
              : 'Completa el formulario para agregar un nuevo colegio'}
          </DialogDescription>
        </DialogHeader>

        <form onSubmit={handleSubmit}>
          <FieldGroup>
            <Field>
              <FieldLabel htmlFor="nombre">Nombre del Colegio</FieldLabel>
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
              {isLoading ? 'Guardando...' : colegio ? 'Actualizar' : 'Crear'}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
