import axiosInstance from '../lib/api/axios-instance';
import { validateApiResponse } from '../lib/api/validator-zod';
import { Asignatura, asignaturaSchema, AsignaturaFormInput, asignaturaFormSchema } from '../schemas/asignatura';
import { z } from 'zod';

const BASE_ASIGNATURAS_PATH = "/api/asignaturas";

/**
 * Obtener todas las asignaturas
 */
export async function getAsignaturas(): Promise<Asignatura[]> {
  const res = await axiosInstance.get(BASE_ASIGNATURAS_PATH);
  const parsed = validateApiResponse<Asignatura[]>(z.array(asignaturaSchema), res.data);
  return parsed;
}

/**
 * Obtener una asignatura por ID
 */
export async function getAsignaturaById(id: number): Promise<Asignatura> {
  const res = await axiosInstance.get(`${BASE_ASIGNATURAS_PATH}/${id}`);
  const parsed = validateApiResponse<Asignatura>(asignaturaSchema, res.data);
  return parsed;
}

/**
 * Crear una nueva asignatura
 */
export async function createAsignatura(input: AsignaturaFormInput): Promise<Asignatura> {
  asignaturaFormSchema.parse(input);
  const res = await axiosInstance.post(BASE_ASIGNATURAS_PATH, input);
  const parsed = validateApiResponse<Asignatura>(asignaturaSchema, res.data);
  return parsed;
}

/**
 * Actualizar una asignatura existente
 */
export async function updateAsignatura(id: number, input: AsignaturaFormInput): Promise<Asignatura> {
  asignaturaFormSchema.parse(input);
  const res = await axiosInstance.put(`${BASE_ASIGNATURAS_PATH}/${id}`, input);
  const parsed = validateApiResponse<Asignatura>(asignaturaSchema, res.data);
  return parsed;
}

/**
 * Eliminar una asignatura
 */
export async function deleteAsignatura(id: number): Promise<void> {
  await axiosInstance.delete(`${BASE_ASIGNATURAS_PATH}/${id}`);
}

const asignaturasService = {
  getAsignaturas,
  getAsignaturaById,
  createAsignatura,
  updateAsignatura,
  deleteAsignatura,
};

export default asignaturasService;
