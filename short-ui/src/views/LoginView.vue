<template>
    <div class="h-screen flex flex-col items-center justify-center gap-4 p-4">
        <UPageCard class="w-full max-w-md">
            <UAuthForm :loading="isPending" :disabled="isPending" :schema="schema" title="Admin Login" :submit="{
                label: 'Sign In',
                color: 'secondary',
            }" description="Enter your credentials to access your account." icon="i-lucide-shield-check"
                :fields="fields" @submit="onSubmit" />
        </UPageCard>
    </div>
</template>

<script setup lang="ts">
import type { FormSubmitEvent, AuthFormField } from '@nuxt/ui'
import * as z from 'zod'
import { useLogin } from '../query/auth.query'
const { mutate, isPending } = useLogin()

const fields: AuthFormField[] = [{
    name: 'username',
    type: 'text',
    label: 'Username',
    placeholder: 'Enter your username',
    required: true,
    color: 'secondary',
}, {
    name: 'password',
    label: 'Password',
    type: 'password',
    placeholder: 'Enter your password',
    required: true,
    color: 'secondary',

}, {
    name: 'remember',
    label: 'Remember me',
    type: 'checkbox',
    color: 'secondary'
}]
const schema = z.object({
    username: z.string('Username is required').min(8, 'Invalid username'),
    password: z.string('Password is required').min(8, 'Must be at least 8 characters')
})
type Schema = z.output<typeof schema>


function onSubmit(payload: FormSubmitEvent<Schema>) {
    mutate(payload.data)

}


</script>