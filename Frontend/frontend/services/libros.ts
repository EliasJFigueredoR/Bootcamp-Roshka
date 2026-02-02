import axiosInstance from '../lib/api/axios-instance';
import { validateApiResponse } from '../lib/api/validator-zod';
import { Libro, libroSchema, LibroFormInput, libroFormSchema } from '../schemas/libros';
import { z } from 'zod';

const BASE_LIBROS_PATH = "/api/libros";

/**
 * Obtener todos los libros
 */
export async function getLibros(): Promise<Libro[]> {
  const res = await axiosInstance.get(BASE_LIBROS_PATH);
  const parsed = validateApiResponse<Libro[]>(z.array(libroSchema), res.data);
  return parsed;
}

/**
 * Obtener un libro por ID
 */
export async function getLibroById(id: number): Promise<Libro> {
  const res = await axiosInstance.get(`${BASE_LIBROS_PATH}/${id}`);
  const parsed = validateApiResponse<Libro>(libroSchema, res.data);
  return parsed;
}

/**
 * Crear un nuevo libro
 */
export async function createLibro(input: LibroFormInput): Promise<Libro> {
  libroFormSchema.parse(input);
  const res = await axiosInstance.post(BASE_LIBROS_PATH, input);
  const parsed = validateApiResponse<Libro>(libroSchema, res.data);
  return parsed;
}

/**
 * Actualizar un libro existente
 */
export async function updateLibro(id: number, input: LibroFormInput): Promise<Libro> {
  libroFormSchema.parse(input);
  const res = await axiosInstance.put(`${BASE_LIBROS_PATH}/${id}`, input);
  const parsed = validateApiResponse<Libro>(libroSchema, res.data);
  return parsed;
}

/**
 * Eliminar un libro
 */
export async function deleteLibro(id: number): Promise<void> {
  await axiosInstance.delete(`${BASE_LIBROS_PATH}/${id}`);
}

const librosService = {
  getLibros,
  getLibroById,
  createLibro,
  updateLibro,
  deleteLibro,
};

export default librosService;
