import { z } from "zod";

/**
 * Esquema para una editorial
 */
export const editorialSchema = z.object({
  id: z.number().optional(),
  nombre: z
    .string({ message: "El nombre de la editorial es obligatorio" })
    .min(1, "El nombre de la editorial no puede estar vacío"),
});

export type Editorial = z.infer<typeof editorialSchema>;

/**
 * Esquema para el formulario de crear/editar editorial
 */
export const editorialFormSchema = z.object({
  nombre: z
    .string({ message: "El nombre de la editorial es obligatorio" })
    .min(1, "El nombre de la editorial no puede estar vacío"),
});

export type EditorialFormInput = z.infer<typeof editorialFormSchema>;
