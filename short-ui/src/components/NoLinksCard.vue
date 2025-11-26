<template>
    <div class="text-center space-y-4">
        <div class="mx-auto h-24 w-24 flex items-center justify-center rounded-full bg-gray-200">
            <Link2 :size="45" class="text-gray-400" />
        </div>
        <span class="text-xl">No short links yet !</span>
    </div>
</template>

<script lang="ts" setup>
import { Link2 } from 'lucide-vue-next';
</script>