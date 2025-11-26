<template>
  <div class="space-y-4">
    <h2 class="text-2xl font-semibold mb-4">Recent Links</h2>
    <LinkItem v-for="url in shortUrls" :url :key="url.id" />
  </div>

</template>

<script setup lang="ts">
import { useShortUrlStore } from '../stores/url.store';
import { storeToRefs } from 'pinia';


const shortUrlStore = useShortUrlStore();

const { shortUrls } = storeToRefs(shortUrlStore);


</script>
