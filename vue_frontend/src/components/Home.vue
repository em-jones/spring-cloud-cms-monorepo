<template>
  <section  class="home wrapper">
    <ul id="post-list">
      <post-summ v-for="post in posts" :key="post.id"
        :post="post"
      >
      </post-summ>
    </ul>
  </section>
</template>

<script>
import PostSumm from '@/components/common/PostSumm';
import AgBase from '@/components/AgBase';
import { mapGetters } from 'vuex';
import symbols from '@/store/symbols';

export default {
  name: 'home',
  components: {
    PostSumm,
    AgBase,
  },
  computed: {
    ...mapGetters({
      posts: symbols.cms.post.filter,
      total: symbols.cms.post.paginate.total,
      totalPages: symbols.cms.post.paginate.totalPages,
      page: symbols.cms.post.paginate.page,
    }),
  },
  data() {
    return {
    };
  },
};
</script>

<style scoped>

</style>
