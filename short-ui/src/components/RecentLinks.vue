<template>
  <div class="space-y-4">
    <h2 class="text-2xl font-semibold mb-4">Recent Links</h2>
    <div
      v-for="url in shortUrls"
      :key="url.id"
      class="p-6 transition-smooth  border-blue-500/50 rounded-lg border bg-card text-gray-600 shadow-sm"
    >
      <div class="space-y-3">
        <div class="flex items-start justify-between gap-4">
          <div class="flex-1 min-w-0">
            <p class="text-sm text-slate-600 mb-1 truncate">
              {{ url.originalUrl }}
            </p>
            <div class="flex items-center gap-2">
              <a
                :href="`${url.shortUrl}`"
                target="_blank"
                class="text-blue-500 hover:text-blue-700 font-medium text-lg transition-smooth flex items-center gap-2"
              >
                {{ url.shortUrl }}
                <ExternalLink class="w-4 h-4" />
              </a>
            </div>
          </div>
          <button
            @click="handleCopy(url.shortUrl, url.id)"
            class="shrink-0 transition-smooth hover:border-blue-500 hover:bg-blue-500/5 inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-gray-900 transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10"
          >
            <Check v-if="copiedId === url.id" class="w-4 h-4 text-green-500" />
            <Copy v-else class="w-4 h-4" />
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { ExternalLink, Check, Copy } from 'lucide-vue-next';
import { useShortUrlStore } from '../stores/url.store';
import { storeToRefs } from 'pinia';

const copiedId = ref<string | null>(null);

const shortUrlStore = useShortUrlStore();

const { shortUrls } = storeToRefs(shortUrlStore);

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
</script>

<style scoped>
.transition-smooth {
  transition: all 0.2s ease-in-out;
}
</style>