'use client'

import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Asignatura, AsignaturaFormInput } from '@/schemas/asignatura';
import asignaturasService from '@/services/asignatura';

const ASIGNATURAS_QUERY_KEY = 'asignaturas';

/**
 * Hook para obtener todas las asignaturas
 */
export function useAsignaturas() {
  return useQuery<Asignatura[], Error>({
    queryKey: [ASIGNATURAS_QUERY_KEY],
    queryFn: asignaturasService.getAsignaturas,
  });
}

/**
 * Hook para obtener una asignatura por ID
 */
export function useAsignatura(id: number) {
  return useQuery<Asignatura, Error>({
    queryKey: [ASIGNATURAS_QUERY_KEY, id],
    queryFn: () => asignaturasService.getAsignaturaById(id),
    enabled: !!id,
  });
}

/**
 * Hook para crear una nueva asignatura
 */
export function useCreateAsignatura() {
  const queryClient = useQueryClient();

  return useMutation<Asignatura, Error, AsignaturaFormInput>({
    mutationFn: asignaturasService.createAsignatura,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [ASIGNATURAS_QUERY_KEY] });
      toast.success('Asignatura creada', {
        description: 'La asignatura se ha creado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al crear asignatura', {
        description: error.message || 'No se pudo crear la asignatura',
      });
    },
  });
}

/**
 * Hook para actualizar una asignatura
 */
export function useUpdateAsignatura() {
  const queryClient = useQueryClient();

  return useMutation<Asignatura, Error, { id: number; data: AsignaturaFormInput }>({
    mutationFn: ({ id, data }) => asignaturasService.updateAsignatura(id, data),
    onSuccess: (_, variables) => {
      queryClient.invalidateQueries({ queryKey: [ASIGNATURAS_QUERY_KEY] });
      queryClient.invalidateQueries({ queryKey: [ASIGNATURAS_QUERY_KEY, variables.id] });
      toast.success('Asignatura actualizada', {
        description: 'La asignatura se ha actualizado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al actualizar asignatura', {
        description: error.message || 'No se pudo actualizar la asignatura',
      });
    },
  });
}

/**
 * Hook para eliminar una asignatura
 */
export function useDeleteAsignatura() {
  const queryClient = useQueryClient();

  return useMutation<void, Error, number>({
    mutationFn: asignaturasService.deleteAsignatura,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [ASIGNATURAS_QUERY_KEY] });
      toast.success('Asignatura eliminada', {
        description: 'La asignatura se ha eliminado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al eliminar asignatura', {
        description: error.message || 'No se pudo eliminar la asignatura',
      });
    },
  });
}
