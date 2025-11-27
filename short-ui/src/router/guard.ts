import { AuthStore } from "./../stores/auth.store";

import type { Router, RouteLocationNormalized } from "vue-router";

export const RouterGuard = (router: Router) => {
  router.beforeEach(
    (to: RouteLocationNormalized, _ , next) => {
      const authStore = AuthStore();
      if (to.meta.requiresAuth && !authStore.isAuthenticated) {
        next({ name: "home" });
        return;
      }
      if (to.name === "admin-login" && authStore.isAuthenticated) {
        next('/');
        return;
      }
      next();
    }
  );
};
