<script setup lang="ts">

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
                <UButton class="py-2 px-4">
                    Logout
                </UButton>
            </template>
        </UHeader>
    </div>
</template>