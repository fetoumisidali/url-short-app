import { type CreateUrlRequest, type ShortUrlResponse } from './../types/url';
import { axiosInstance } from "./axios";

export const createShortUrl = async (url: CreateUrlRequest) => {
  const { data } = await axiosInstance.post<ShortUrlResponse>('/api/v1/shorter', url);
  return data;
};