import { z } from "zod";

/**
 * Esquema para un libro
 */
export const libroSchema = z.object({
  id: z.number().optional(),
  nombre: z
    .string({ message: "El nombre del libro es obligatorio" })
    .min(1, "El nombre del libro no puede estar vacío"),
  cantidad: z
    .number({ message: "La cantidad es obligatoria" })
    .int("La cantidad debe ser un número entero")
    .min(0, "La cantidad no puede ser negativa"),
});

export type Libro = z.infer<typeof libroSchema>;

/**
 * Esquema para el formulario de crear/editar libro
 */
export const libroFormSchema = z.object({
  nombre: z
    .string({ message: "El nombre del libro es obligatorio" })
    .min(1, "El nombre del libro no puede estar vacío"),
  cantidad: z
    .number({ message: "La cantidad es obligatoria" })
    .int("La cantidad debe ser un número entero")
    .min(0, "La cantidad no puede ser negativa"),
});

export type LibroFormInput = z.infer<typeof libroFormSchema>;
