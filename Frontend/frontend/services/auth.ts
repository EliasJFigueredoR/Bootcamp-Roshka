import axiosInstance, { setAuthToken, getAuthToken } from '../lib/api/axios-instance';
import { validateApiResponse } from '../lib/api/validator-zod';
import {LoginInput, loginSchema,
        RegisterInput,  registerSchema, 
        AuthResponse, authResponseSchema} from '../schemas/auth';

const BASE_AUTH_PATH = "/auth";

export async function login (input: LoginInput): Promise<AuthResponse> {
  loginSchema.parse(input);

  const res = await axiosInstance.post(`${BASE_AUTH_PATH}/login`, input);
  const parsed = validateApiResponse<AuthResponse>(authResponseSchema, res.data);

  setAuthToken(parsed.token);

  return parsed;
}

export async function register(input: RegisterInput): Promise<AuthResponse> {
  registerSchema.parse(input);

  const res = await axiosInstance.post<AuthResponse>(`${BASE_AUTH_PATH}/register`, input);
  const parsed = validateApiResponse<AuthResponse>(authResponseSchema, res.data);

  setAuthToken(parsed.token);

  return parsed;
}

export async function logout(): Promise<void> {  
  try {
    await axiosInstance.post<void>(`${BASE_AUTH_PATH}/logout`);
  } catch (err) {
    console.log(err)
    // Si el backend no soporta logout, simplemente ignoramos el error y limpiamos el token local
  } finally {
    setAuthToken(null);
  }
}

export function getToken(): string | null {
  return getAuthToken();
}

export function isAuthenticated(): boolean {
  return Boolean(getAuthToken());
}

const authService = {
  login,
  register,
  logout,
  getToken,
  isAuthenticated,
};

export default authService;