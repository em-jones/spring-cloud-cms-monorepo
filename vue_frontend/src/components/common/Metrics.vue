<template>
  <div>
  {{ `${ created }
    - ${post.wordCount} words - ${Math.ceil(post.wordCount / 200)} minutes read` }}
  </div>
</template>

<script>
import moment from 'moment';

export default {
  computed: {
    created() {
      return moment(this.post.createdAt).format('LLLL');
    },
  },
  name: 'metrics',
  props: ['post', 'count'],
};
</script>

<style scoped>

</style>
