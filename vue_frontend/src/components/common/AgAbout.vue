<template>
  <div>
  <transition name="fadeDown">
    <h1 v-if="contentLoaded">{{ title }}</h1>
  </transition>
  <transition name="fadeDown">
    <p v-if="contentLoaded">{{ about }}</p>
  </transition>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import symbols from '@/store/symbols';

export default {
  computed: {
    ...mapGetters({
      contentLoaded: symbols.cms.content.loaded,
    }),
  },
  methods: {
    ...mapActions({
      getContent: symbols.cms.content.view,
    }),
  },
  async mounted() {
    if (!this.contentLoaded) {
      const campaign = this.$localStorage.get('campaign');
      await this.getContent(JSON.parse(campaign));
    }
  },
  name: 'ag-about',
  props:
  {
    title: { default: 'It\'s Always Easier Than it Looks' },
    about: { default: 'One of the biggest problems I’ll ever face is picking a path and sticking to it only as long as I know it’ll produce the results I anticipate. I’m a deducer; a hacker at times, and a software engineer at others. Here is where I’ll keep track of all my projects as a way to look back at where I’ve come from, and a way to remind myself of how much harder it was the first time.' },
  },
};
</script>
