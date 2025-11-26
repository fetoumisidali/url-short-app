<template>
    <form @submit.prevent="handleSubmit">
        <UCard class="p-2 mb-8 border border-blue-400">
            <div class="flex gap-3">
                <UInput color="secondary" v-model="form.url" type="text" placeholder="Enter you URL here" class="w-full"
                    :ui="{ base: 'h-14 px-3 py-2' }" />
                <UButton :disabled="!form.url.trim()" color="secondary" type="submit" class="px-8 text-lg"
                    :loading="isPending" trailing>
                    Short
                </UButton>
            </div>
        </UCard>
    </form>

</template>

<script lang="ts" setup>

import { useUrlForm } from "../composables/useUrlForm";

const { form, handleSubmit, isPending } = useUrlForm();

</script>