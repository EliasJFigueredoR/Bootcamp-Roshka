'use client'

import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Editorial, EditorialFormInput } from '@/schemas/editorial';
import editorialesService from '@/services/editorial';

const EDITORIALES_QUERY_KEY = 'editoriales';

/**
 * Hook para obtener todas las editoriales
 */
export function useEditoriales() {
  return useQuery<Editorial[], Error>({
    queryKey: [EDITORIALES_QUERY_KEY],
    queryFn: editorialesService.getEditoriales,
  });
}

/**
 * Hook para obtener una editorial por ID
 */
export function useEditorial(id: number) {
  return useQuery<Editorial, Error>({
    queryKey: [EDITORIALES_QUERY_KEY, id],
    queryFn: () => editorialesService.getEditorialById(id),
    enabled: !!id,
  });
}

/**
 * Hook para crear una nueva editorial
 */
export function useCreateEditorial() {
  const queryClient = useQueryClient();

  return useMutation<Editorial, Error, EditorialFormInput>({
    mutationFn: editorialesService.createEditorial,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [EDITORIALES_QUERY_KEY] });
      toast.success('Editorial creada', {
        description: 'La editorial se ha creado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al crear editorial', {
        description: error.message || 'No se pudo crear la editorial',
      });
    },
  });
}

/**
 * Hook para actualizar una editorial
 */
export function useUpdateEditorial() {
  const queryClient = useQueryClient();

  return useMutation<Editorial, Error, { id: number; data: EditorialFormInput }>({
    mutationFn: ({ id, data }) => editorialesService.updateEditorial(id, data),
    onSuccess: (_, variables) => {
      queryClient.invalidateQueries({ queryKey: [EDITORIALES_QUERY_KEY] });
      queryClient.invalidateQueries({ queryKey: [EDITORIALES_QUERY_KEY, variables.id] });
      toast.success('Editorial actualizada', {
        description: 'La editorial se ha actualizado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al actualizar editorial', {
        description: error.message || 'No se pudo actualizar la editorial',
      });
    },
  });
}

/**
 * Hook para eliminar una editorial
 */
export function useDeleteEditorial() {
  const queryClient = useQueryClient();

  return useMutation<void, Error, number>({
    mutationFn: editorialesService.deleteEditorial,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [EDITORIALES_QUERY_KEY] });
      toast.success('Editorial eliminada', {
        description: 'La editorial se ha eliminado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al eliminar editorial', {
        description: error.message || 'No se pudo eliminar la editorial',
      });
    },
  });
}
