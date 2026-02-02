import axiosInstance from '../lib/api/axios-instance';
import { validateApiResponse } from '../lib/api/validator-zod';
import { Colegio, colegioSchema, ColegioFormInput, colegioFormSchema } from '../schemas/colegio';
import { z } from 'zod';

const BASE_COLEGIOS_PATH = "/api/colegios";

/**
 * Obtener todos los colegios
 */
export async function getColegios(): Promise<Colegio[]> {
  const res = await axiosInstance.get(BASE_COLEGIOS_PATH);
  const parsed = validateApiResponse<Colegio[]>(z.array(colegioSchema), res.data);
  return parsed;
}

/**
 * Obtener un colegio por ID
 */
export async function getColegioById(id: number): Promise<Colegio> {
  const res = await axiosInstance.get(`${BASE_COLEGIOS_PATH}/${id}`);
  const parsed = validateApiResponse<Colegio>(colegioSchema, res.data);
  return parsed;
}

/**
 * Crear un nuevo colegio
 */
export async function createColegio(input: ColegioFormInput): Promise<Colegio> {
  colegioFormSchema.parse(input);
  const res = await axiosInstance.post(BASE_COLEGIOS_PATH, input);
  const parsed = validateApiResponse<Colegio>(colegioSchema, res.data);
  return parsed;
}

/**
 * Actualizar un colegio existente
 */
export async function updateColegio(id: number, input: ColegioFormInput): Promise<Colegio> {
  colegioFormSchema.parse(input);
  const res = await axiosInstance.put(`${BASE_COLEGIOS_PATH}/${id}`, input);
  const parsed = validateApiResponse<Colegio>(colegioSchema, res.data);
  return parsed;
}

/**
 * Eliminar un colegio
 */
export async function deleteColegio(id: number): Promise<void> {
  await axiosInstance.delete(`${BASE_COLEGIOS_PATH}/${id}`);
}

const colegiosService = {
  getColegios,
  getColegioById,
  createColegio,
  updateColegio,
  deleteColegio,
};

export default colegiosService;
