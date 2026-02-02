'use client'

import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Aula, AulaFormInput } from '@/schemas/aula';
import aulasService from '@/services/aula';

const AULAS_QUERY_KEY = 'aulas';

/**
 * Hook para obtener todas las aulas
 */
export function useAulas() {
  return useQuery<Aula[], Error>({
    queryKey: [AULAS_QUERY_KEY],
    queryFn: aulasService.getAulas,
  });
}

/**
 * Hook para obtener un aula por ID
 */
export function useAula(id: number) {
  return useQuery<Aula, Error>({
    queryKey: [AULAS_QUERY_KEY, id],
    queryFn: () => aulasService.getAulaById(id),
    enabled: !!id,
  });
}

/**
 * Hook para crear una nueva aula
 */
export function useCreateAula() {
  const queryClient = useQueryClient();

  return useMutation<Aula, Error, AulaFormInput>({
    mutationFn: aulasService.createAula,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [AULAS_QUERY_KEY] });
      toast.success('Aula creada', {
        description: 'El aula se ha creado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al crear aula', {
        description: error.message || 'No se pudo crear el aula',
      });
    },
  });
}

/**
 * Hook para actualizar un aula
 */
export function useUpdateAula() {
  const queryClient = useQueryClient();

  return useMutation<Aula, Error, { id: number; data: AulaFormInput }>({
    mutationFn: ({ id, data }) => aulasService.updateAula(id, data),
    onSuccess: (_, variables) => {
      queryClient.invalidateQueries({ queryKey: [AULAS_QUERY_KEY] });
      queryClient.invalidateQueries({ queryKey: [AULAS_QUERY_KEY, variables.id] });
      toast.success('Aula actualizada', {
        description: 'El aula se ha actualizado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al actualizar aula', {
        description: error.message || 'No se pudo actualizar el aula',
      });
    },
  });
}

/**
 * Hook para eliminar un aula
 */
export function useDeleteAula() {
  const queryClient = useQueryClient();

  return useMutation<void, Error, number>({
    mutationFn: aulasService.deleteAula,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [AULAS_QUERY_KEY] });
      toast.success('Aula eliminada', {
        description: 'El aula se ha eliminado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al eliminar aula', {
        description: error.message || 'No se pudo eliminar el aula',
      });
    },
  });
}
