'use client'

import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Profesor, ProfesorFormInput } from '@/schemas/profesor';
import profesoresService from '@/services/profesor';

const PROFESORES_QUERY_KEY = 'profesores';

/**
 * Hook para obtener todos los profesores
 */
export function useProfesores() {
  return useQuery<Profesor[], Error>({
    queryKey: [PROFESORES_QUERY_KEY],
    queryFn: profesoresService.getProfesores,
  });
}

/**
 * Hook para obtener un profesor por ID
 */
export function useProfesor(id: number) {
  return useQuery<Profesor, Error>({
    queryKey: [PROFESORES_QUERY_KEY, id],
    queryFn: () => profesoresService.getProfesorById(id),
    enabled: !!id,
  });
}

/**
 * Hook para crear un nuevo profesor
 */
export function useCreateProfesor() {
  const queryClient = useQueryClient();

  return useMutation<Profesor, Error, ProfesorFormInput>({
    mutationFn: profesoresService.createProfesor,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [PROFESORES_QUERY_KEY] });
      toast.success('Profesor creado', {
        description: 'El profesor se ha creado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al crear profesor', {
        description: error.message || 'No se pudo crear el profesor',
      });
    },
  });
}

/**
 * Hook para actualizar un profesor
 */
export function useUpdateProfesor() {
  const queryClient = useQueryClient();

  return useMutation<Profesor, Error, { id: number; data: ProfesorFormInput }>({
    mutationFn: ({ id, data }) => profesoresService.updateProfesor(id, data),
    onSuccess: (_, variables) => {
      queryClient.invalidateQueries({ queryKey: [PROFESORES_QUERY_KEY] });
      queryClient.invalidateQueries({ queryKey: [PROFESORES_QUERY_KEY, variables.id] });
      toast.success('Profesor actualizado', {
        description: 'El profesor se ha actualizado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al actualizar profesor', {
        description: error.message || 'No se pudo actualizar el profesor',
      });
    },
  });
}

/**
 * Hook para eliminar un profesor
 */
export function useDeleteProfesor() {
  const queryClient = useQueryClient();

  return useMutation<void, Error, number>({
    mutationFn: profesoresService.deleteProfesor,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [PROFESORES_QUERY_KEY] });
      toast.success('Profesor eliminado', {
        description: 'El profesor se ha eliminado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al eliminar profesor', {
        description: error.message || 'No se pudo eliminar el profesor',
      });
    },
  });
}
