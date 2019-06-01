<template>
  <div class="page-tags" id="tags">
    <tag v-for="tag in tags" :tag="tag" :key="tag.id"></tag>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import Tag from '@/components/common/Tag';
import AgBase from '@/components/AgBase';
import symbols from '@/store/symbols';

export default {
  name: 'tags',
  components: {
    Tag,
    AgBase,

  },
  computed: {
    ...mapGetters({
      tags: symbols.cms.tags.list,
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
