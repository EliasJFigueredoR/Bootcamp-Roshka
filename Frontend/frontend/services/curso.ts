import axiosInstance from '../lib/api/axios-instance';
import { validateApiResponse } from '../lib/api/validator-zod';
import { Curso, cursoSchema, CursoFormInput, cursoFormSchema } from '../schemas/curso';
import { z } from 'zod';

const BASE_CURSOS_PATH = "/api/cursos";

/**
 * Obtener todos los cursos
 */
export async function getCursos(): Promise<Curso[]> {
  const res = await axiosInstance.get(BASE_CURSOS_PATH);
  const parsed = validateApiResponse<Curso[]>(z.array(cursoSchema), res.data);
  return parsed;
}

/**
 * Obtener un curso por ID
 */
export async function getCursoById(id: number): Promise<Curso> {
  const res = await axiosInstance.get(`${BASE_CURSOS_PATH}/${id}`);
  const parsed = validateApiResponse<Curso>(cursoSchema, res.data);
  return parsed;
}

/**
 * Crear un nuevo curso
 */
export async function createCurso(input: CursoFormInput): Promise<Curso> {
  cursoFormSchema.parse(input);
  const res = await axiosInstance.post(BASE_CURSOS_PATH, input);
  const parsed = validateApiResponse<Curso>(cursoSchema, res.data);
  return parsed;
}

/**
 * Actualizar un curso existente
 */
export async function updateCurso(id: number, input: CursoFormInput): Promise<Curso> {
  cursoFormSchema.parse(input);
  const res = await axiosInstance.put(`${BASE_CURSOS_PATH}/${id}`, input);
  const parsed = validateApiResponse<Curso>(cursoSchema, res.data);
  return parsed;
}

/**
 * Eliminar un curso
 */
export async function deleteCurso(id: number): Promise<void> {
  await axiosInstance.delete(`${BASE_CURSOS_PATH}/${id}`);
}

const cursosService = {
  getCursos,
  getCursoById,
  createCurso,
  updateCurso,
  deleteCurso,
};

export default cursosService;
