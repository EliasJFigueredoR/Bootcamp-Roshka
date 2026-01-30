import axios, { AxiosError, AxiosInstance, AxiosResponse, InternalAxiosRequestConfig } from 'axios';

const API_BASE: string | undefined = process.env.NEXT_PUBLIC_API_URL;

const axiosInstance: AxiosInstance = axios.create({
  baseURL: API_BASE,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json',
    'X-Requested-With': 'XMLHttpRequest',
  },
});

const isBrowser: boolean = typeof window !== 'undefined';

function getTokenFromStorage(): string | null {
  if (!isBrowser) return null;
  return localStorage.getItem('token') || null;
}

axiosInstance.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    const token: string | null = getTokenFromStorage();
    if (token && config.headers) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error: AxiosError) => Promise.reject(error)
);

axiosInstance.interceptors.response.use(
  (response: AxiosResponse) => response,
  (error: AxiosError) => {
    if (error.response) {
      const status: number = error.response.status;
      if (status === 401) {
        //Aquí se podria colocar la logica del refresh token
        if (isBrowser) {
          localStorage.removeItem('token');
        }
      }
    }

    const isNetworkError: boolean = !error.response;
    const friendlyMessage: string = isNetworkError
      ? 'Network error: check your connection'
      : // Try common shapes: { message } or { error: { message } }
        (error.response as AxiosResponse)?.data?.message ||
        (error.response as AxiosResponse)?.data?.error ||
        error.message;

    const enriched = {
      original: error,
      status: error.response?.status ?? null,
      friendlyMessage,
    };

    return Promise.reject(enriched);
  }
);

/**
 * Call this after login/logout to keep storage and instance in sync.
 * - `token`: JWT string or null to clear.
 */
export function setAuthToken(token: string | null) {
  if (!isBrowser) return;
  if (token) {
    localStorage.setItem('token', token);
    axiosInstance.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  } else {
    localStorage.removeItem('token');
    delete axiosInstance.defaults.headers.common['Authorization'];
  }
}

export function getAuthToken(): string | null {
  return getTokenFromStorage();
}

export default axiosInstance;