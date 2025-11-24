import { useMutation } from "@tanstack/vue-query";
import { createShortUrl } from "../api/url.api";

export const useCreateShortUrl = () => {
  return useMutation({
    mutationFn: createShortUrl,
  });
};
