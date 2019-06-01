import Vuex from 'vuex';
import Vue from 'vue';
// modules
import post from './modules/post';
import user from './modules/user';
import content from './modules/content';

Vue.use(Vuex);

const state = {
  currentPage: '',
  tags: {},
  postForm: {
    title: '',
    content: '',
    tags: [],
  },
};

const modules = {
  post, user, content,
};


export default new Vuex.Store({
  state, modules,
});
