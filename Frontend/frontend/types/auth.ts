/**
 * Usuario autenticado - representa los datos del usuario en el sistema
 * Este es el objeto que recibes del backend después de login/register exitoso
 */
export interface User {
  id: string;                    // ID único del usuario en la BD
  username: string;              // Nombre de usuario para login
}

/**
 * Respuesta exitosa del servidor al hacer login o register
 * Incluye el token JWT y los datos del usuario
 */
export interface AuthResponse {
  token: string;                 // JWT token para autenticar requests
  user: User;                    // Datos del usuario autenticado
  expiresIn?: number;            // Tiempo de expiración del token (en segundos)
}

/**
 * Respuesta de error del servidor
 * Estructura común para manejar errores de la API
 */
export interface AuthError {
  message: string;               // Mensaje de error legible
  code?: string;                 // Código de error (ej: "INVALID_CREDENTIALS")
  statusCode?: number;           // HTTP status code (401, 400, etc.)
}

/**
 * Estado del contexto de autenticación
 * Lo que almacenas en tu AuthContext/Provider
 */
export interface AuthState {
  user: User | null;             // Usuario actual (null si no está autenticado)
  token: string | null;          // Token JWT actual (null si no hay sesión)
  isLoading: boolean;            // True mientras verifica la sesión inicial
  isAuthenticated: boolean;      // True si hay un usuario logueado
}