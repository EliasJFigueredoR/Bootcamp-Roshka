import { z } from "zod";

/**
 * Esquema para un aula
 */
export const aulaSchema = z.object({
  id: z.number().optional(),
  nombre: z
    .string({ message: "El nombre del aula es obligatorio" })
    .min(1, "El nombre del aula no puede estar vacío"),
});

export type Aula = z.infer<typeof aulaSchema>;

/**
 * Esquema para el formulario de crear/editar aula
 */
export const aulaFormSchema = z.object({
  nombre: z
    .string({ message: "El nombre del aula es obligatorio" })
    .min(1, "El nombre del aula no puede estar vacío"),
});

export type AulaFormInput = z.infer<typeof aulaFormSchema>;
