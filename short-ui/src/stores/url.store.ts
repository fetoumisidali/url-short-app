import { defineStore } from "pinia";
import { ref } from "vue";
import type { Url } from "../types/url";

export const useShortUrlStore = defineStore(
  "shortUrl",
  () => {
    const shortUrls = ref<Url[]>([]);

    function getExistingUrl(shortUrl: string, originalUrl: string) {
      return shortUrls.value.find(
        (url) => url.shortUrl === shortUrl || url.originalUrl === originalUrl
      );
    }
    function getExistOriginalUrl(originalUrl: string){
        return shortUrls.value.find(
        (url) => url.originalUrl === originalUrl
      );
    }

    function addUrl(shortUrl: string, originalUrl: string) {
      if (!getExistingUrl(shortUrl, originalUrl)) {
        shortUrls.value.unshift({
          id: crypto.randomUUID(),
          shortUrl,
          originalUrl,
        });
      }
    }

    return {
      shortUrls,
      addUrl,
      getExistOriginalUrl
    };
  },
  {
    persist: {
      key: "my-short-url-list",
      storage: localStorage,
      pick: ["shortUrls"],
    },
  }
);
