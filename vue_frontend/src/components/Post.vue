<template>
  <section class="wrapper">
    <article class="post">
      <header>
        <h1>
          {{ post.title }}
        </h1>
        <h2>
          {{ post.subtitle }}
        </h2>
        <h2 class="headline">
          <metrics :post="post">
          </metrics>
          <span class="tags">
          <router-link
              v-for="tag in post.tags"
              :to="`/tags/${tag.id}`"
              :key="tag.id"
          >
            {{ `${tag.name}  ` }}
          </router-link>
        </span>
        </h2>
      </header>
      <div v-html="post.content"></div>
    </article>
  </section>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import Metrics from '@/components/common/Metrics';
import symbols from '@/store/symbols';

export default {
  components: {
    Metrics,
  },
  computed: {
    ...mapGetters({
      post: symbols.cms.post.view,
    }),
  },
  methods: {
    ...mapActions({
      getPosts: symbols.cms.post.list,
      setPosts: symbols.cms.post.set,
      setPost: symbols.cms.post.view,
    }),
  },
  mounted() {
    this.setPost(this.$route.params.id);
  },
  name: 'post',
};
</script>

