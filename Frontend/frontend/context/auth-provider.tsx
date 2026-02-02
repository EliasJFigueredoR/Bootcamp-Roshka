'use client'
import React, { createContext, useEffect, useState, ReactNode, useCallback } from 'react';
import axiosInstance, { setAuthToken } from '../lib/api/axios-instance';
import authService from '../services/auth';
import type { LoginInput, RegisterInput, User} from '../schemas/auth';
import type {AuthContextValue} from '../types/auth'


export const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode })
{
    const [user, setUser] = useState<User | null>(null);
    const [token, setToken] = useState<string | null>(() => authService.getToken());
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const isAuthenticated = Boolean(user && token);

    useEffect(() => {
        let mounted: boolean = true;

        async function init() {
            setIsLoading(true);
            const existingToken: string | null = authService.getToken();

            if (!existingToken) {
                setToken(null);
                setUser(null);
                setIsLoading(false);
                return;
            }

            try {
                setAuthToken(existingToken); // sincroniza axios headers + localStorage
               
                const res = await axiosInstance.get('/auth/me');
                const fetchedUser: User = res.data?.user ?? res.data;

                if (mounted) {
                setUser(fetchedUser);
                setToken(existingToken);
                }

            } catch (err) {
                // token inválido o expirado -> limpiar
                console.log(err);

                setAuthToken(null);
                if (mounted) {
                setUser(null);
                setToken(null);
                }
            } finally {
                if (mounted) setIsLoading(false);
            }
        }

        init();
        
        return () => {
            mounted = false;
        };

    }, []);

    const login = useCallback(async (input: LoginInput) => {
        const resp = await authService.login(input); // setAuthToken ya se llama en el service
        setUser(resp.user);
        setToken(resp.token);
        return resp.user;
    }, []);

    const register = useCallback(async (input: RegisterInput) => {
        const resp = await authService.register(input);
        setUser(resp.user);
        setToken(resp.token);
        return resp.user;
    }, []);

    const logout = useCallback(async () => {
        await authService.logout(); // limpia token desde el service
        setUser(null);
        setToken(null);
    }, []);

    const value: AuthContextValue = {
        user,
        token,
        isLoading,
        isAuthenticated,
        login,
        register,
        logout,
    };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
