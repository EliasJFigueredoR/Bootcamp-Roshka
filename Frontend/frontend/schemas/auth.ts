import { z } from "zod";

/**
 * Esquema para el formulario de login
 */
export const loginSchema = z.object({
  username: z
    .string({ message: "El nombre de usuario es obligatorio" })
    .min(1, "El nombre de usuario no puede estar vacío"),
  password: z
    .string({ message: "La contraseña es obligatoria" })
    .min(6, "La contraseña debe tener al menos 6 caracteres"),
});

export type LoginInput = z.infer<typeof loginSchema>;

/**
 * Esquema para el formulario de registro
 */
export const registerSchema = z
  .object({
    username: z
      .string({ message: "El nombre de usuario es obligatorio" })
      .min(1, "El nombre de usuario debe tener al menos 1 caracter"),
    password: z
      .string({ message: "La contraseña es obligatoria" })
      .min(6, "La contraseña debe tener al menos 6 caracteres"),
    confirmPassword: z.string({
      message: "La confirmación de contraseña es obligatoria",
    }),
  })
  .refine((data) => data.password === data.confirmPassword, {
    path: ["confirmPassword"],
    message: "Las contraseñas no coinciden",
  });

export type RegisterInput = z.infer<typeof registerSchema>;

