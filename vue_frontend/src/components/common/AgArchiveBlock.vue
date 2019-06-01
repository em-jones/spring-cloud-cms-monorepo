<template>
  <div>
    <h3>{{ year }}</h3>
    <div v-for="post in posts" :key="post.id" class="post-item">
      <div class="post-time">{{ post.day }}</div>
      <router-link :to="`/post/${post.id}`" class="post-link">
       {{ post.title }}
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ag-archive-block',
  props: ['year', 'posts'],
};
</script>

<style scoped>

</style>
