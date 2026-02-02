'use client'

import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Curso, CursoFormInput } from '@/schemas/curso';
import cursosService from '@/services/curso';

const CURSOS_QUERY_KEY = 'cursos';

/**
 * Hook para obtener todos los cursos
 */
export function useCursos() {
  return useQuery<Curso[], Error>({
    queryKey: [CURSOS_QUERY_KEY],
    queryFn: cursosService.getCursos,
  });
}

/**
 * Hook para obtener un curso por ID
 */
export function useCurso(id: number) {
  return useQuery<Curso, Error>({
    queryKey: [CURSOS_QUERY_KEY, id],
    queryFn: () => cursosService.getCursoById(id),
    enabled: !!id,
  });
}

/**
 * Hook para crear un nuevo curso
 */
export function useCreateCurso() {
  const queryClient = useQueryClient();

  return useMutation<Curso, Error, CursoFormInput>({
    mutationFn: cursosService.createCurso,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [CURSOS_QUERY_KEY] });
      toast.success('Curso creado', {
        description: 'El curso se ha creado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al crear curso', {
        description: error.message || 'No se pudo crear el curso',
      });
    },
  });
}

/**
 * Hook para actualizar un curso
 */
export function useUpdateCurso() {
  const queryClient = useQueryClient();

  return useMutation<Curso, Error, { id: number; data: CursoFormInput }>({
    mutationFn: ({ id, data }) => cursosService.updateCurso(id, data),
    onSuccess: (_, variables) => {
      queryClient.invalidateQueries({ queryKey: [CURSOS_QUERY_KEY] });
      queryClient.invalidateQueries({ queryKey: [CURSOS_QUERY_KEY, variables.id] });
      toast.success('Curso actualizado', {
        description: 'El curso se ha actualizado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al actualizar curso', {
        description: error.message || 'No se pudo actualizar el curso',
      });
    },
  });
}

/**
 * Hook para eliminar un curso
 */
export function useDeleteCurso() {
  const queryClient = useQueryClient();

  return useMutation<void, Error, number>({
    mutationFn: cursosService.deleteCurso,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [CURSOS_QUERY_KEY] });
      toast.success('Curso eliminado', {
        description: 'El curso se ha eliminado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al eliminar curso', {
        description: error.message || 'No se pudo eliminar el curso',
      });
    },
  });
}
