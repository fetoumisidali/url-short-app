import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const AuthStore = defineStore("auth", () => {
  const token = ref<string | null>(localStorage.getItem("auth_token") ?? null);

  const isAuthenticated = computed(() => !!token.value);

  const setToken = (newToken: string | null) => {
    token.value = newToken;
    if (newToken) {
      localStorage.setItem("auth_token", newToken);
    } else {
      localStorage.removeItem("auth_token");
    }
  };

  const logout = () => {
    setToken(null);
  };

  return {
    logout,
    setToken,
    token,
    isAuthenticated,
  };
});
