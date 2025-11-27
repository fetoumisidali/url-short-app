import { useMutation, useQuery } from "@tanstack/vue-query";
import { createShortUrl, getAllUrls } from "../api/url.api";

export const useCreateShortUrl = () => {
  return useMutation({
    mutationFn: createShortUrl,
  });
};

export const useGetAllUrls = () => {
  return useQuery({
    queryKey: ['all-urls'],
    queryFn: getAllUrls,
  })
}
