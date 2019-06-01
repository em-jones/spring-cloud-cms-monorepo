<template>
    <div class="profile">
      <section class="wrapper">
          <header id="header">
              <ag-avatar></ag-avatar>
              <transition name="fadeUp">
                <ag-title :value="content.title" v-if="showTitle && contentLoaded"></ag-title>
              </transition>
              <transition name="fadeUp">
                <ag-subtitle :value="content.subtitle" v-if="showSubtitle && contentLoaded">
                </ag-subtitle>
              </transition>
          </header>
      </section>
      <section class="wrapper home">
        <slot></slot>
      </section>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import moment from 'moment';
import symbols from '@/store/symbols';
import AgAvatar from '@/components/common/AgAvatar';
import AgTitle from '@/components/common/AgTitle';
import AgSubtitle from '@/components/common/AgSubtitle';

export default {
  components: {
    AgAvatar, AgTitle, AgSubtitle,
  },
  computed: {
    showTitle() {
      return !this.noTitle.includes(this.$route.name);
    },
    showSubtitle() {
      return !this.noTitle.includes(this.$route.name);
    },
    ...mapGetters({
      content: symbols.cms.content.view,
      contentLoaded: symbols.cms.content.loaded,
    }),
  },
  data() {
    return {
      noTitle: ['about', 'post'],
    };
  },
  methods: {
    ...mapActions({
      getPosts: symbols.cms.post.list,
      setPosts: symbols.cms.post.set,
      getContent: symbols.cms.content.view,
      setContent: symbols.cms.content.set,
    }),
  },
  async mounted() {
    if (!this.content.id) {
      const campaign = this.$localStorage.get('campaign');
      await this.getContent(JSON.parse(campaign));
    }
    const string = this.$localStorage.get('posts', JSON.stringify({ loaded: moment().subtract(1, 'days').format(), posts: [] }));
    const { loaded, posts } = JSON.parse(string);
    const minDiff = moment(loaded).diff(moment(), 'minutes');
    if (minDiff <= -1440 || Object.keys(posts).length === 0) { // day old
      this.getPosts();
    } else {
      this.setPosts(posts);
    }
  },
  name: 'ag-base',
};
</script>

<style scoped>

</style>
