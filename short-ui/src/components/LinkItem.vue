<template>
    <UCard class="border border-blue-400">
        <div class="space-y-4  p-2 md:p-0">
            <div class="flex items-start justify-between gap-4">
                <div class="flex-1 min-w-0">
                    <p class="text-xs md:text-sm text-gray-500 dark:text-gray-400 mb-1 truncate">
                        {{ url.originalUrl }}
                    </p>

                    <div class="flex items-center gap-2">
                        <ULink :to="`${url.shortUrl}`" target="_blank"
                            class="text-blue-500 hover:text-blue-600 dark:text-blue-400 dark:hover:text-blue-500 font-medium text-sm md:text-lg flex items-center gap-2"
                            :ui="{ text: 'hover:text-current' }">
                            {{ url.shortUrl }}
                            <UIcon name="i-heroicons-arrow-up-right-20-solid" class="w-4 h-4" />
                        </ULink>
                    </div>
                </div>

                <UButton color="neutral" variant="outline" class="h-10 w-10 flex items-center justify-center"
                    @click="handleCopy(url.shortUrl, url.id)" :tooltip="copiedId === url.id ? 'Copied!' : 'Copy Link'">
                    <Check v-if="copiedId === url.id" class="w-4 h-4 text-green-500" />
                    <Copy v-else class="w-4 h-4" />
                </UButton>
                <UButton color="error" variant="outline" class="h-10 w-10 flex items-center justify-center"
                    @click="handleDelete(url.id)">
                    <Trash class="w-4 h-4" />
                </UButton>
            </div>
        </div>
    </UCard>
</template>

<script lang="ts" setup>
import { Check, Copy, Trash } from 'lucide-vue-next';
import { ref } from 'vue';
import { useShortUrlStore } from '../stores/url.store';

const copiedId = ref<string | null>(null);

interface url {
    originalUrl: string,
    shortUrl: string,
    id: string
}
 defineProps<{url:url}>()

const handleCopy = async (shortened: string, id: string) => {
    try {
        await navigator.clipboard.writeText(shortened);
        copiedId.value = id;
        setTimeout(() => {
            copiedId.value = null;
        }, 2000);
    } catch (err) {
        console.error('Failed to copy:', err);
    }
};

const handleDelete = (id:string) => {
    deleteUrl(id)
}

const { deleteUrl } = useShortUrlStore()

</script>

<style scoped>
.transition-smooth {
    transition: all 0.2s ease-in-out;
}
</style>