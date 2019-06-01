<template>
  <div class="archive">
    <ag-archive-block :key="year" v-for="year in Object.keys(posts)"
      :year="year"
      :posts="posts[year]"
    ></ag-archive-block>
  </div>
</template>

<script>
/* eslint-disable import/extensions */
import { mapGetters } from 'vuex';
import AgBase from '@/components/AgBase';
import AgArchiveBlock from '@/components/common/AgArchiveBlock';
import symbols from '@/store/symbols';

export default {
  name: 'archive',
  computed: {
    ...mapGetters({
      archive: symbols.cms.post.archive,
    }),
    posts() {
      const { selectedTags } = this.$route.query;
      if (selectedTags) {
        // eslint-disable-next-line radix
        const tagSet = new Set(selectedTags.map(tag => parseInt(tag)));
        const reducer = (acc, [year, posts]) => {
          const obj = {};
          obj[year] = posts
            .filter(({ tags }) => {
              const hasTag = tags.filter(({ id }) => tagSet.has(id));
              return hasTag.length > 0;
            });
          return { ...acc, ...obj };
        };
        return Object.entries(this.archive).reduce(reducer, {});
      }
      return this.archive;
    },
  },
  components: {
    AgBase,
    AgArchiveBlock,
  },
};
</script>

<style scoped>

</style>
