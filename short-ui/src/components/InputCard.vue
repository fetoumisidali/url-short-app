<template>
    <form @submit.prevent="handleSubmit">
        <div class="p-8 mb-8 rounded-lg border border-blue-500">
            <div class="flex gap-3">
                <input v-model="form.url" type="text" placeholder="Enter you URL here"
                    class=" h-14 w-full px-3 py-2 rounded-md border border-blue-300 focus:outline-none focus:ring-2 focus:ring-blue-500">
                <button type="submit"
                    class="text-white font-medium bg-blue-500 rounded-lg h-14 px-8 text-lg not-disabled:cursor-pointer hover:opacity-90 transition-colors duration-300 disabled:opacity-50 disabled:cursor-not-allowed">
                    Short
                </button>
            </div>
        </div>
    </form>

</template>

<script lang="ts" setup>
import { reactive } from 'vue';
import { useCreateShortUrl } from '../query/url.query';
import { useShortUrlStore } from '../stores/url.store';

const { addUrl ,getExistOriginalUrl } = useShortUrlStore()

const form = reactive({
    url: ''
})

const createShortUrlMutation = useCreateShortUrl()

function handleSubmit() {
    if(getExistOriginalUrl(form.url)) return;
    createShortUrlMutation.mutate(form, {
        onError: () => {
            console.log("something wrong")
        },
        onSuccess: (data) => {
            console.log(data)
            addUrl(
                data.shortUrl,
                form.url
            )
            form.url = ''
        }
    })
}

</script>