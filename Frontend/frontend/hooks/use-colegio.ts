'use client'

import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Colegio, ColegioFormInput } from '@/schemas/colegio';
import colegiosService from '@/services/colegio';

const COLEGIOS_QUERY_KEY = 'colegios';

/**
 * Hook para obtener todos los colegios
 */
export function useColegios() {
  return useQuery<Colegio[], Error>({
    queryKey: [COLEGIOS_QUERY_KEY],
    queryFn: colegiosService.getColegios,
  });
}

/**
 * Hook para obtener un colegio por ID
 */
export function useColegio(id: number) {
  return useQuery<Colegio, Error>({
    queryKey: [COLEGIOS_QUERY_KEY, id],
    queryFn: () => colegiosService.getColegioById(id),
    enabled: !!id,
  });
}

/**
 * Hook para crear un nuevo colegio
 */
export function useCreateColegio() {
  const queryClient = useQueryClient();

  return useMutation<Colegio, Error, ColegioFormInput>({
    mutationFn: colegiosService.createColegio,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [COLEGIOS_QUERY_KEY] });
      toast.success('Colegio creado', {
        description: 'El colegio se ha creado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al crear colegio', {
        description: error.message || 'No se pudo crear el colegio',
      });
    },
  });
}

/**
 * Hook para actualizar un colegio
 */
export function useUpdateColegio() {
  const queryClient = useQueryClient();

  return useMutation<Colegio, Error, { id: number; data: ColegioFormInput }>({
    mutationFn: ({ id, data }) => colegiosService.updateColegio(id, data),
    onSuccess: (_, variables) => {
      queryClient.invalidateQueries({ queryKey: [COLEGIOS_QUERY_KEY] });
      queryClient.invalidateQueries({ queryKey: [COLEGIOS_QUERY_KEY, variables.id] });
      toast.success('Colegio actualizado', {
        description: 'El colegio se ha actualizado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al actualizar colegio', {
        description: error.message || 'No se pudo actualizar el colegio',
      });
    },
  });
}

/**
 * Hook para eliminar un colegio
 */
export function useDeleteColegio() {
  const queryClient = useQueryClient();

  return useMutation<void, Error, number>({
    mutationFn: colegiosService.deleteColegio,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [COLEGIOS_QUERY_KEY] });
      toast.success('Colegio eliminado', {
        description: 'El colegio se ha eliminado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al eliminar colegio', {
        description: error.message || 'No se pudo eliminar el colegio',
      });
    },
  });
}
