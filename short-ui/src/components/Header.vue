<script setup lang="ts">
import { Link2 } from 'lucide-vue-next';

</script>

<template>
    <div class="text-center mb-12">
        <div
            class="inline-flex items-center justify-center w-16 h-16 rounded-full bg-blue-500 mb-3 shadow-glow text-white">
            <Link2 :size="30" />
        </div>
        <h1 class="text-5xl font-bold mb-3 text-blue-500">
            URL Short
        </h1>
        <p class="text-gray-600 text-lg">
            Short your links and share them easily
        </p>
    </div>
</template>