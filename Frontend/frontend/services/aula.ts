import axiosInstance from '../lib/api/axios-instance';
import { validateApiResponse } from '../lib/api/validator-zod';
import { Aula, aulaSchema, AulaFormInput, aulaFormSchema } from '../schemas/aula';
import { z } from 'zod';

const BASE_AULAS_PATH = "/api/aulas";

/**
 * Obtener todas las aulas
 */
export async function getAulas(): Promise<Aula[]> {
  const res = await axiosInstance.get(BASE_AULAS_PATH);
  const parsed = validateApiResponse<Aula[]>(z.array(aulaSchema), res.data);
  return parsed;
}

/**
 * Obtener un aula por ID
 */
export async function getAulaById(id: number): Promise<Aula> {
  const res = await axiosInstance.get(`${BASE_AULAS_PATH}/${id}`);
  const parsed = validateApiResponse<Aula>(aulaSchema, res.data);
  return parsed;
}

/**
 * Crear una nueva aula
 */
export async function createAula(input: AulaFormInput): Promise<Aula> {
  aulaFormSchema.parse(input);
  const res = await axiosInstance.post(BASE_AULAS_PATH, input);
  const parsed = validateApiResponse<Aula>(aulaSchema, res.data);
  return parsed;
}

/**
 * Actualizar un aula existente
 */
export async function updateAula(id: number, input: AulaFormInput): Promise<Aula> {
  aulaFormSchema.parse(input);
  const res = await axiosInstance.put(`${BASE_AULAS_PATH}/${id}`, input);
  const parsed = validateApiResponse<Aula>(aulaSchema, res.data);
  return parsed;
}

/**
 * Eliminar un aula
 */
export async function deleteAula(id: number): Promise<void> {
  await axiosInstance.delete(`${BASE_AULAS_PATH}/${id}`);
}

const aulasService = {
  getAulas,
  getAulaById,
  createAula,
  updateAula,
  deleteAula,
};

export default aulasService;
