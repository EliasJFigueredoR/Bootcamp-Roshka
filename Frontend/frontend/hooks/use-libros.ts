'use client'

import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Libro, LibroFormInput } from '@/schemas/libros';
import librosService from '@/services/libros';

const LIBROS_QUERY_KEY = 'libros';

/**
 * Hook para obtener todos los libros
 */
export function useLibros() {
  return useQuery<Libro[], Error>({
    queryKey: [LIBROS_QUERY_KEY],
    queryFn: librosService.getLibros,
  });
}

/**
 * Hook para obtener un libro por ID
 */
export function useLibro(id: number) {
  return useQuery<Libro, Error>({
    queryKey: [LIBROS_QUERY_KEY, id],
    queryFn: () => librosService.getLibroById(id),
    enabled: !!id,
  });
}

/**
 * Hook para crear un nuevo libro
 */
export function useCreateLibro() {
  const queryClient = useQueryClient();

  return useMutation<Libro, Error, LibroFormInput>({
    mutationFn: librosService.createLibro,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [LIBROS_QUERY_KEY] });
      toast.success('Libro creado', {
        description: 'El libro se ha creado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al crear libro', {
        description: error.message || 'No se pudo crear el libro',
      });
    },
  });
}

/**
 * Hook para actualizar un libro
 */
export function useUpdateLibro() {
  const queryClient = useQueryClient();

  return useMutation<Libro, Error, { id: number; data: LibroFormInput }>({
    mutationFn: ({ id, data }) => librosService.updateLibro(id, data),
    onSuccess: (_, variables) => {
      queryClient.invalidateQueries({ queryKey: [LIBROS_QUERY_KEY] });
      queryClient.invalidateQueries({ queryKey: [LIBROS_QUERY_KEY, variables.id] });
      toast.success('Libro actualizado', {
        description: 'El libro se ha actualizado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al actualizar libro', {
        description: error.message || 'No se pudo actualizar el libro',
      });
    },
  });
}

/**
 * Hook para eliminar un libro
 */
export function useDeleteLibro() {
  const queryClient = useQueryClient();

  return useMutation<void, Error, number>({
    mutationFn: librosService.deleteLibro,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [LIBROS_QUERY_KEY] });
      toast.success('Libro eliminado', {
        description: 'El libro se ha eliminado exitosamente',
      });
    },
    onError: (error) => {
      toast.error('Error al eliminar libro', {
        description: error.message || 'No se pudo eliminar el libro',
      });
    },
  });
}
