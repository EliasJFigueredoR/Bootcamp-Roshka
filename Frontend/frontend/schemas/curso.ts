import { z } from "zod";

/**
 * Esquema para un curso
 */
export const cursoSchema = z.object({
  id: z.number().optional(),
  nombre: z
    .string({ message: "El nombre del curso es obligatorio" })
    .min(1, "El nombre del curso no puede estar vacío"),
});

export type Curso = z.infer<typeof cursoSchema>;

/**
 * Esquema para el formulario de crear/editar curso
 */
export const cursoFormSchema = z.object({
  nombre: z
    .string({ message: "El nombre del curso es obligatorio" })
    .min(1, "El nombre del curso no puede estar vacío"),
});

export type CursoFormInput = z.infer<typeof cursoFormSchema>;
