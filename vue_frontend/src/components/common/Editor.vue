<template>
  <section class="wrapper">
    <article class="post">
      <div>
        <header>
          <h1 @click="input = !input" v-if="!input">
            {{ post.title || 'No Title' }}
          </h1>
          <input style="font-size: 24px;"
                 @blur="input = !input" @keyup="saveTitle" v-else type="text" v-model="post.title"/>
          <h2 @click="sInput = !sInput" v-if="!sInput">
            {{ post.subtitle || "No Subtitle" }}
          </h2>
          <input style="font-size: 20px;"
                 v-else type="text"
                 @blur="sInput = !sInput"
                 @keyup="saveSubtitle" v-model="post.subtitle"/>
          <h2 class="headline">
            <metrics v-if="post"  :post="post"></metrics>
            <span class="tags">
            <router-link
                v-for="tag in tags"
                :to="`/tags/${tag.id}`"
                :key="tag.id"
            >
              {{ `${tag.name}  ` }}
            </router-link>
               <multiselect v-if="editing"
                            v-model="tags"
                            :options="opts"
                            :multiple="true"
                            :taggable="true"
                            :close-on-select="false"
                            placeholder="Tags"
                            label="Tags"
                            track-by="name"
                            :custom-label="customLabel"
                            @tag="addTag"
               >
               </multiselect>
          </span>
          </h2>
        </header>
        <div v-if="editing">
          <div class="toolbar">
          </div>
          <div class="editor" v-html="content" >
          </div>
        </div>
        <div v-html="post.content" v-else></div>
      </div>
    </article>
    <button class="f6 link dim br1 ph3 pv2 mb2 dib white bg-light-purple"
            @click="savePost">Save</button>
  </section>
</template>

<script>
import Vue from 'vue';
import Quill from 'quill';
import Multiselect from 'vue-multiselect';
import { debounce } from 'lodash';
import { mapGetters, mapActions } from 'vuex';
import symbols from '@/store/symbols';
import str from '@/utils/str';
import Image from '@/utils/Image';
import Metrics from '@/components/common/Metrics';

const toolbar = [
  ['bold', 'italic', 'underline', 'strike'], // toggled buttons
  ['blockquote', 'code-block'],
  ['image'],
  [{ header: 1 }, { header: 2 }], // custom button values
  [{ list: 'ordered' }, { list: 'bullet' }],
  [{ script: 'sub' }, { script: 'super' }], // superscript/subscript
  [{ indent: '-1' }, { indent: '+1' }], // outdent/indent
  [{ direction: 'rtl' }], // text direction
  [{ size: ['small', false, 'large', 'huge'] }], // custom dropdown
  [{ header: [1, 2, 3, 4, 5, 6, false] }],
  [{ color: [] }, { background: [] }], // dropdown with defaults from theme
  [{ font: [] }],
  [{ align: [] }],
  ['clean'], // remove formatting button
];

export default {
  components: {
    Metrics,
    Multiselect,
  },
  computed: {
    ...mapGetters({
      opts: symbols.cms.tags.select,
      post: symbols.cms.post.view,
    }),
  },
  data() {
    return {
      editing: true,
      input: false,
      sInput: false,
      focused: this.autofocus,
      editor: null,
      count: 0,
      tags: [],
    };
  },
  methods: {
    ...mapActions({
      create: symbols.cms.post.create,
    }),
    customLabel(option) {
      return `${option.name}`;
    },
    addTag(tag) {
      const name = tag.split(' ').map(word => str.initcap(word)).join(' ');
      if (!this.opts.concat(this.tags)
        .map(item => item.name).includes(name)) {
        Vue.set(this.opts, this.opts.length, { name });
        Vue.set(this.tags, this.tags.length, { name });
      }
    },
    saveTitle(e) {
      if (e.key === 'Enter') {
        this.input = !this.input;
      }
    },
    saveSubtitle(e) {
      if (e.key === 'Enter') {
        this.sInput = !this.sInput;
      }
    },
    calcCount() {
      this.post.wordCount = this.editor.getContents()
        .ops.reduce((acc, { insert }) => `${acc}${insert}`, '').split(' ').length;
    },
    savePost() {
      const content = document.querySelector('.ql-editor').innerHTML;
      const { post, tags, count } = this;
      post.content = content;
      post.wordCount = count;
      this.create({ post, tags });
    },
    saveDraft() {
      // eslint-disable-next-line
      //todo
    },
    promptDraft() {
      // eslint-disable-next-line
      //todo
    },
  },
  mounted() {
    const el = document.querySelector('.editor');
    this.editor = new Quill(el, this.options);
    this.editor.on('text-change', debounce(this.calcCount, 1000));
    const toolbarModule = this.editor.getModule('toolbar');
    toolbarModule.addHandler('image', Image.handleImageUpload());
  },
  name: 'editor',
  props: {
    options: {
      type: Object,
      default: () => ({
        modules: {
          syntax: {
            highlight: text => window.hljs.highlightAuto(text).value,
          },
          toolbar,
        },
        theme: 'snow' }),
    },
    autofocus: Boolean,
    content: '',
  },
  watch: {
    focused(val) {
      this.editor[val ? 'focus' : 'blur']();
    },
  },
};
</script>

