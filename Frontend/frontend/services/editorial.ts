import axiosInstance from '../lib/api/axios-instance';
import { validateApiResponse } from '../lib/api/validator-zod';
import { Editorial, editorialSchema, EditorialFormInput, editorialFormSchema } from '../schemas/editorial';
import { z } from 'zod';

const BASE_EDITORIALES_PATH = "/api/editoriales";

/**
 * Obtener todas las editoriales
 */
export async function getEditoriales(): Promise<Editorial[]> {
  const res = await axiosInstance.get(BASE_EDITORIALES_PATH);
  const parsed = validateApiResponse<Editorial[]>(z.array(editorialSchema), res.data);
  return parsed;
}

/**
 * Obtener una editorial por ID
 */
export async function getEditorialById(id: number): Promise<Editorial> {
  const res = await axiosInstance.get(`${BASE_EDITORIALES_PATH}/${id}`);
  const parsed = validateApiResponse<Editorial>(editorialSchema, res.data);
  return parsed;
}

/**
 * Crear una nueva editorial
 */
export async function createEditorial(input: EditorialFormInput): Promise<Editorial> {
  editorialFormSchema.parse(input);
  const res = await axiosInstance.post(BASE_EDITORIALES_PATH, input);
  const parsed = validateApiResponse<Editorial>(editorialSchema, res.data);
  return parsed;
}

/**
 * Actualizar una editorial existente
 */
export async function updateEditorial(id: number, input: EditorialFormInput): Promise<Editorial> {
  editorialFormSchema.parse(input);
  const res = await axiosInstance.put(`${BASE_EDITORIALES_PATH}/${id}`, input);
  const parsed = validateApiResponse<Editorial>(editorialSchema, res.data);
  return parsed;
}

/**
 * Eliminar una editorial
 */
export async function deleteEditorial(id: number): Promise<void> {
  await axiosInstance.delete(`${BASE_EDITORIALES_PATH}/${id}`);
}

const editorialesService = {
  getEditoriales,
  getEditorialById,
  createEditorial,
  updateEditorial,
  deleteEditorial,
};

export default editorialesService;
