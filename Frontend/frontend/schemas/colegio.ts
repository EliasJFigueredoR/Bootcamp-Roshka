import { z } from "zod";

/**
 * Esquema para un colegio
 */
export const colegioSchema = z.object({
  id: z.number().optional(),
  nombre: z
    .string({ message: "El nombre del colegio es obligatorio" })
    .min(1, "El nombre del colegio no puede estar vacío"),
});

export type Colegio = z.infer<typeof colegioSchema>;

/**
 * Esquema para el formulario de crear/editar colegio
 */
export const colegioFormSchema = z.object({
  nombre: z
    .string({ message: "El nombre del colegio es obligatorio" })
    .min(1, "El nombre del colegio no puede estar vacío"),
});

export type ColegioFormInput = z.infer<typeof colegioFormSchema>;
