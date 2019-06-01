<template>
  <ag-about :title="content.aboutTitle" :about="content.about"></ag-about>
</template>

<script>
import { mapGetters } from 'vuex';
import AgBase from '@/components/AgBase';
import AgAbout from '@/components/common/AgAbout';
import symbols from '@/store/symbols';

export default {
  computed: {
    ...mapGetters({
      content: symbols.cms.content.view,
    }),
  },
  name: 'about',
  components: {
    AgBase, AgAbout,
  },
};
</script>

<style scoped>

</style>
