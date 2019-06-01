<template>
    <li>
      <a :href='`/post/${post.id}`'>
        <aside class="dates">{{ post.createdAt | moment('dddd, MMMM Do YYYY') }}</aside>
      </a>
      <router-link :to='`/post/${post.id}`'>
        <span v-html="post.title"></span><h2 v-if="post.subtitle">{{ post.subtitle }}</h2>
      </router-link>
      <button class="f6 link dim br1 ph3 pv2 mb2 dib white bg-light-purple"
              @click="editPost">Edit</button>
      <button class="f6 link dim br1 ph3 pv2 mb2 dib white bg-light-purple"
              @click="deletePost">Delete</button>
    </li>
</template>

<script>
import { mapActions } from 'vuex';
import symbols from '@/store/symbols';

export default {
  data() {
    return {
      id: this.post.id,
    };
  },
  methods: {
    ...mapActions({
      del: symbols.cms.post.del,
      edit: symbols.cms.post.edit,
    }),
    deletePost() {
      this.del(this.id);
    },
  },
  name: 'post-summ',
  props: ['post'],
};
</script>

<style scoped>

</style>
