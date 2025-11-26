<template>
    <form @submit.prevent="handleSubmit">
        <UCard class="p-2 mb-8 border border-blue-400">
            <div class="flex gap-3">
                <UInput color="secondary" v-model="form.url" type="text" placeholder="Enter you URL here" class="w-full"
                    :ui="{ base: 'h-14 px-3 py-2' }" />
                <UButton color="secondary" type="submit" class="px-8 text-lg"
                    :loading="createShortUrlMutation.isPending.value" trailing>
                    Short
                </UButton>
            </div>
        </UCard>
    </form>

</template>

<script lang="ts" setup>
import { reactive } from 'vue';
import { useCreateShortUrl } from '../query/url.query';
import { useShortUrlStore } from '../stores/url.store';

const { addUrl, getExistOriginalUrl } = useShortUrlStore()

const form = reactive({
    url: ''
})

const createShortUrlMutation = useCreateShortUrl()
const toast = useToast()

function handleSubmit() {
    if (getExistOriginalUrl(form.url)) {
        toast.add({
            title:"Url already exists",
            description:"the url you enter is already exists",
            icon:"i-lucide-info",
            color:"info"
        })
        return;
    };
    createShortUrlMutation.mutate(form, {
        onError: (error) => {
            toast.add({
                title: 'Something went wrong',
                description: "please try again later",
                icon:"i-lucide-shield-x",
                color:"error"
            })
            console.log(error)
            console.log(error.message)
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