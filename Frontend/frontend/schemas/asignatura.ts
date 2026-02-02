import { z } from "zod";

/**
 * Esquema para una asignatura
 */
export const asignaturaSchema = z.object({
  id: z.number().optional(),
  nombre: z
    .string({ message: "El nombre de la asignatura es obligatorio" })
    .min(1, "El nombre de la asignatura no puede estar vacío"),
});

export type Asignatura = z.infer<typeof asignaturaSchema>;

/**
 * Esquema para el formulario de crear/editar asignatura
 */
export const asignaturaFormSchema = z.object({
  nombre: z
    .string({ message: "El nombre de la asignatura es obligatorio" })
    .min(1, "El nombre de la asignatura no puede estar vacío"),
});

export type AsignaturaFormInput = z.infer<typeof asignaturaFormSchema>;
