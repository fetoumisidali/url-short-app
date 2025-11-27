<script setup lang="ts">
import { useRouter } from 'vue-router';
import { AuthStore } from '../../stores/auth.store';


    const authStore = AuthStore()
    const router = useRouter()
    function handleLogout(){
        authStore.logout()
        router.push({name:'home'})
    }
</script>

<template>
    <div class="w-full max-w-6xl mx-auto">
        <UHeader class="border-b-0">
            <template #title>
                <router-link to="/admin-dashboard">
                    <h1 class="text-3xl flex items-center gap-2">
                        Short
                    </h1>
                </router-link>
            </template>

            <template #right>
                <UButton @click="handleLogout" class="py-2 px-4">
                    Logout
                </UButton>
            </template>
        </UHeader>
    </div>
</template>

