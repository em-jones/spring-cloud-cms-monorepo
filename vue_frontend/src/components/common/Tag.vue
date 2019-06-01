<template>
  <span class="tags">
    <router-link
        :to="{ name: 'tag-filter', query: { selectedTags: [tag.id, tag.id + 1] }}"
        style="font-size: 10pt; color: rgb(187, 187, 187);">
      {{ `${tag.name}(${tag.count})` }}
    </router-link>
  </span>
</template>

<script>
export default {
  name: 'tag',
  props: ['tag'],
};

</script>

