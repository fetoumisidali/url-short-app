import { type CreateUrlRequest, type DatabaseUrl, type ShortUrlResponse } from './../types/url';
import { axiosInstance } from "./axios";

export const createShortUrl = async (url: CreateUrlRequest) => {
  const { data } = await axiosInstance.post<ShortUrlResponse>('/api/v1/shorter', url);
  return data;
};

export const getAllUrls = async () => {
  const { data } = await axiosInstance.get<DatabaseUrl[]>('/api/v1/shorter');
  return data;
}