import { z } from "zod";

/**
 * Esquema para un profesor
 */
export const profesorSchema = z.object({
  id: z.number().optional(),
  nombre: z
    .string({ message: "El nombre del profesor es obligatorio" })
    .min(1, "El nombre del profesor no puede estar vacío"),
});

export type Profesor = z.infer<typeof profesorSchema>;

/**
 * Esquema para el formulario de crear/editar profesor
 */
export const profesorFormSchema = z.object({
  nombre: z
    .string({ message: "El nombre del profesor es obligatorio" })
    .min(1, "El nombre del profesor no puede estar vacío"),
});

export type ProfesorFormInput = z.infer<typeof profesorFormSchema>;
