<template>
    <UForm :schema="urlSchema" :state="form" @submit.prevent="handleSubmit">
        <UCard class="p-2 mb-8 border border-blue-400">
            <div class="flex gap-3 items-stat">
                <UFormField class="w-full" name="url" help="start with http://." required>
                    <UInput color="secondary" v-model="form.url" type="text" placeholder="Enter you URL here"
                        class="w-full" :ui="{ base: 'h-14 px-3 py-2' }" :disabled="isPending">
                        <template v-if="form.url?.length" #trailing>
                            <UButton color="neutral" variant="link" size="sm" icon="i-lucide-circle-x"
                                aria-label="Clear input" @click="form.url = ''" />
                        </template>
                    </UInput>
                </UFormField>
                <UButton :disabled="!isValid" color="secondary" type="submit" class="max-h-14 px-8 text-lg"
                    :loading="isPending" trailing>
                    Short
                </UButton>
            </div>
        </UCard>
    </UForm>

</template>

<script lang="ts" setup>

import { useUrlForm } from "../composables/useUrlForm";
import { urlSchema } from "../schema/url.schema";

const { form, handleSubmit, isPending, isValid } = useUrlForm();

</script>