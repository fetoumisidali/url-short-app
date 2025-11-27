<script setup lang="ts">
import { useGetAllUrls } from '../../query/url.query';
import { type DatabaseUrl } from '../../types/url'
import type { ColumnDef } from '@tanstack/vue-table'

const columns: ColumnDef<DatabaseUrl>[] = [
    {
        header: 'ID',
        accessorKey: 'id',

    },
    {
        header: 'Long URL',
        accessorKey: 'longUrl',
    },
    {
        header: 'Short ID',
        accessorKey: 'shortId',
    },
    {
        header: 'Created At',
        accessorKey: 'createdAt',
        cell: ({ row }) => {
            return new Date(row.getValue('createdAt')).toLocaleString('en-US', {
                day: 'numeric',
                month: 'short',
                hour: '2-digit',
                minute: '2-digit'
            })
        }
    },
];

const { data: links, error, isLoading } = useGetAllUrls()
</script>

<template>

    <div v-if="isLoading">
        Loading...
    </div>
    <div v-else-if="links && links.length === 0">
        No Url Found
    </div>
    <div v-else-if="links && links.length > 0">
        <UTable :data="links" :columns class="w-full" />
    </div>
    <div v-else-if="error">
        Error loading URLs.
    </div>
</template>