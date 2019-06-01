<template>
  <ag-list
    :items="contentList"
    @select="edit"
  ></ag-list>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import symbols from '@/store/symbols';
import AgList from '@/components/common/AgList';

export default {
  components: {
    AgList,
  },
  computed: {
    ...mapGetters({
      contentList: symbols.cms.content.list,
    }),
  },
  methods: {
    edit(id) {
      this.$router.push(`/content/${id}`);
    },
    ...mapActions({
      loadList: symbols.cms.content.list,
    }),
  },
  mounted() {
    this.loadList();
  },
};
</script>
