import {
  createRouter,
  createWebHistory,
  type RouteRecordRaw,
} from "vue-router";
import { RouterGuard } from "./guard";

const routes: RouteRecordRaw[] = [
  {
    path:'/admin-login',
    name:'admin-login',
    component: () => import("../views/LoginView.vue"),
  },
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
  },
  {
    path: "/admin-dashboard",
    name: "admin-dashboard",
    meta: { requiresAuth: true },
    component: () => import("../views/AdminDashboardView.vue"),
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

RouterGuard(router);

export default router;
