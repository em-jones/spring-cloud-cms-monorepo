<template>
  <form class="measure center">
    <!-- title, subtitle, about, aboutTitle, avatar, ab, anon -->
    <fieldset id="content" class="ba b--transparent ph0 mh0">
      <legend class="f4 fw6 ph0 mh0">Content Editor</legend>
      <ag-input
        name="title"
        id="title"
        type="text"
        label="Title"
        :value="title"
        @updated="setTitle"
      ></ag-input>
      <ag-input
        name="subtitle"
        id="subtitle"
        type="text"
        label="Subtitle"
        :value="subtitle"
        @updated="setSubTitle"
      ></ag-input>
      <ag-input
        name="aboutTitle"
        id="aboutTitle"
        type="text"
        label="About Title"
        :value="aboutTitle"
        @updated="setAboutTitle"
      ></ag-input>
      <ag-input
        :textarea="true"
        name="about"
        id="about"
        label="About"
        :value="about"
        @updated="setAbout"
      ></ag-input>
      <label class="pa0 ma0 lh-copy f6 pointer">
        <input name="anonymous" :model="anonymous" type="checkbox"> Anonymous
      </label>
    </fieldset>
    <div class="">
      <input
        class="b ph3 pv2 input-reset ba b--black bg-transparent grow pointer f6 dib"
        @click="saveContent" value="Save Content">
    </div>
  </form>
</template>

<script>
import { mapActions } from 'vuex';
import AgBase from '@/components/AgBase';
import AgInput from '@/components/common/AgInput';
import symbols from '@/store/symbols';

export default {
  components: {
    AgBase,
    AgInput,
  },
  methods: {
    ...mapActions({
      save: symbols.cms.content.save,
      get: symbols.cms.content.view,
    }),
    async saveContent() {
      const { title, subtitle, aboutTitle, about, id, avatar } = this;
      const status = await this.save({ title, subtitle, aboutTitle, about, id, avatar });
      if (status) {
        this.$router.push('home');
      }
    },
    setTitle(e) {
      this.title = e.target.value;
    },
    setSubTitle(e) {
      this.subtitle = e.target.value;
    },
    setAboutTitle(e) {
      this.aboutTitle = e.target.value;
    },
    setAbout(e) {
      this.about = e.target.value;
    },
    setAvatar(e) {
      this.avatar = e.target.value;
    },
    init({ title, subtitle, avatar, about, aboutTitle, anon, ab }) {
      this.title = title;
      this.subtitle = subtitle;
      this.avatar = avatar;
      this.about = about;
      this.aboutTitle = aboutTitle;
      this.anon = anon;
      this.ab = ab;
    },
  },
  data() {
    return {
      id: '',
      title: '',
      subtitle: '',
      aboutTitle: '',
      about: '',
      ab: '',
      anonymous: '',
      avatar: '/static/images/avatar.svg',
    };
  },
  async mounted() {
    this.id = this.$route.params.id;
    if (this.id) {
      const content = await this.get(this.id);
      this.init(content);
    }
  },
};
</script>
