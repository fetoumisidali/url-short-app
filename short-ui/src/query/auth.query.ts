import { useMutation, useQueryClient } from "@tanstack/vue-query";
import { login } from "../api/auth.api";
import { AuthStore } from "../stores/auth.store";
import { useRouter } from "vue-router";
import { useToast } from "@nuxt/ui/runtime/composables/useToast.js";

export const useLogin = () => {
  const router = useRouter();
  const authStore = AuthStore();
  const toast = useToast();
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: login,
    onSuccess: (data) => {
      authStore.setToken(data.token);
      queryClient.invalidateQueries();
      router.push({ name: "admin-dashboard" });
    },
    onError: () => {
      toast.add({
        title: "error",
        description: "error something went wrong please try again later",
        color: "error",
      });
    },
  });
};


