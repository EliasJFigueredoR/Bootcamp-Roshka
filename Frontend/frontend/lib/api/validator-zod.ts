import { z } from "zod";

/**
 * Helper para validar respuestas de API con mensajes claros en español.
 * Lanza un Error con mensajes en cadena si la validación falla.
 */
export function validateApiResponse<T>(
  schema: z.ZodType<T>,
  data: unknown
): T {
  try {
    return schema.parse(data);
  } catch (error) {
    if (error instanceof z.ZodError) {
      const mensajes = error.issues.map((issue) => {
        const path = issue.path.length ? issue.path.join(".") : "respuesta";
        return `${path}: ${issue.message}`;
      });
      throw new Error(mensajes.join(" | "));
    }
    throw error;
  }
}