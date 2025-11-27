import { type AuthRequest, type AuthResponse } from "./../types/auth";
import { axiosInstance } from "./axios";

export const login = async (authRequest: AuthRequest) => {
  const { data } = await axiosInstance.post<AuthResponse>(
    "/api/admin/auth/login",
    authRequest
  );
  return data;
};
