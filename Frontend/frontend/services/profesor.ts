import axiosInstance from '../lib/api/axios-instance';
import { validateApiResponse } from '../lib/api/validator-zod';
import { Profesor, profesorSchema, ProfesorFormInput, profesorFormSchema } from '../schemas/profesor';
import { z } from 'zod';

const BASE_PROFESORES_PATH = "/api/profesores";

/**
 * Obtener todos los profesores
 */
export async function getProfesores(): Promise<Profesor[]> {
  const res = await axiosInstance.get(BASE_PROFESORES_PATH);
  const parsed = validateApiResponse<Profesor[]>(z.array(profesorSchema), res.data);
  return parsed;
}

/**
 * Obtener un profesor por ID
 */
export async function getProfesorById(id: number): Promise<Profesor> {
  const res = await axiosInstance.get(`${BASE_PROFESORES_PATH}/${id}`);
  const parsed = validateApiResponse<Profesor>(profesorSchema, res.data);
  return parsed;
}

/**
 * Crear un nuevo profesor
 */
export async function createProfesor(input: ProfesorFormInput): Promise<Profesor> {
  profesorFormSchema.parse(input);
  const res = await axiosInstance.post(BASE_PROFESORES_PATH, input);
  const parsed = validateApiResponse<Profesor>(profesorSchema, res.data);
  return parsed;
}

/**
 * Actualizar un profesor existente
 */
export async function updateProfesor(id: number, input: ProfesorFormInput): Promise<Profesor> {
  profesorFormSchema.parse(input);
  const res = await axiosInstance.put(`${BASE_PROFESORES_PATH}/${id}`, input);
  const parsed = validateApiResponse<Profesor>(profesorSchema, res.data);
  return parsed;
}

/**
 * Eliminar un profesor
 */
export async function deleteProfesor(id: number): Promise<void> {
  await axiosInstance.delete(`${BASE_PROFESORES_PATH}/${id}`);
}

const profesoresService = {
  getProfesores,
  getProfesorById,
  createProfesor,
  updateProfesor,
  deleteProfesor,
};

export default profesoresService;
