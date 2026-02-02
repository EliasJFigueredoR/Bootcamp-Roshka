import { User } from "lucide-react";
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

/**
 * Usuario autenticado - representa los datos del usuario en el sistema
 * Este es el objeto que recibes del backend después de login/register exitoso
 */

export const UserSchema = z.object(
  {id: z
    .uuid({ message: "El id de usuario es obligatorio" }),
    username: z
    .string({ message: "El nombre de usuario es obligatorio" })
    .min(1, "El nombre de usuario debe tener al menos un caracter"),
  }
)

export type User = z.infer<typeof UserSchema>;

/**
 * Respuesta exitosa del servidor al hacer login o register
 * Incluye el token JWT y los datos del usuario
 */
export const authResponseSchema = z.object({
  token: z.string(),
  user: UserSchema,
  expiresIn: z.number().optional(),
});

export type AuthResponse = z.infer<typeof authResponseSchema>;


/**
 * Respuesta de error del servidor
 * Estructura común para manejar errores de la API
 */

export const AuthError = z.object({
  message: z.string(),
  code: z.string().optional(),
  statusCode: z.number().optional(),
});

export type AuthError = z.infer<typeof AuthError>;

/**
 * Estado del contexto de autenticación
 * Lo que almacenas en tu AuthContext/Provider
 */

export const AuthState = z.object({
  user: UserSchema.nullable,
  token: z.string().nullable(),
  isLoading: z.boolean(),
  isAuthenticated: z.boolean(),
});

export type AuthState = z.infer<typeof AuthState>;