import { defineStore } from "pinia";
import { ref } from "vue";
import type { Url } from "../types/url";
import { v4 as uuidv4 } from 'uuid';


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
          id: uuidv4(),
          shortUrl,
          originalUrl,
        });
      }
    }

    function deleteUrl(id: string) {
      shortUrls.value = shortUrls.value.filter((url) => url.id !== id);
    }


    return {
      shortUrls,
      addUrl,
      deleteUrl,
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
