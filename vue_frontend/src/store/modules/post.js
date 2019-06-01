/* eslint-disable no-shadow,consistent-return,import/first */
import Vue from 'vue';
import moment from 'moment';
import { Post, Tags } from '@/utils';
import symbols from '../symbols';
import { post } from '../../api';

const state = {
  posts: {},
  currentPost: '',
  page: 0,
  total: 0,
  totalPages: 0,
  perPage: 10,
};

const getters = {
  [symbols.cms.post.list]: s => s.posts,
  [symbols.cms.post.filter]: (s) => {
    const first = s.page * s.perPage;
    const last = (s.page + 1) * s.perPage;
    return Object.values(s.posts).filter((_, index) => index >= first && index < last);
  },
  [symbols.cms.post.paginate.totalPages]: s => s.totalPages,
  [symbols.cms.post.paginate.total]: s => s.total,
  [symbols.cms.post.archive]: state => Post.archive(state),
  [symbols.cms.tags.list]: state => Tags.list(state),
  [symbols.cms.tags.select]: state => Tags.select(state),
  [symbols.cms.post.view]: (state) => {
    const [post] = Object.values(state.posts).filter(({ id }) => id === state.currentPost);
    if (!post) {
      return {
        title: '',
        subtitle: '',
        wordCount: 0,
        tags: [],
      };
    }
    return post;
  },
};

const mutations = {
  [symbols.cms.post.view]: (s, id) => {
    // eslint-disable-next-line radix
    Vue.set(s, 'currentPost', parseInt(id));
  },
  [symbols.cms.post.list](s, list) {
    Object.values(list)
      .forEach((item) => {
        Vue.set(s.posts, item.id, item);
      });
  },
  [symbols.cms.post.del](s, id) {
    Vue.set(s.post, id, null);
  },
  [symbols.cms.post.paginate.nextPage]: (s) => {
    Vue.set(s, 'page', s.page + 1);
  },
  [symbols.cms.post.paginate.prevPage]: (s) => {
    Vue.set(s, 'page', s.page - 1);
  },
};

const actions = {
  [symbols.cms.post.view]: ({ commit }, id) => {
    commit(symbols.cms.post.view, id);
  },
  async [symbols.cms.post.create](_, newPost) {
    const { data } = await post.create(newPost);
    return data;
  },
  async [symbols.cms.post.list]({ commit, state }) {
    if (Object.values(state.posts).length > 0) {
      return null;
    }
    const { data } = await post.list();
    const { list } = data;
    commit(symbols.cms.post.list, list);
    const save = { loaded: moment().add(1, 'day').format(), posts: list };
    Vue.localStorage.set('posts', JSON.stringify(save));
  },
  [symbols.cms.post.set]: ({ commit }, posts) => {
    commit(symbols.cms.post.list, posts);
  },
  async [symbols.cms.post.del]({ commit }, id) {
    const { data, status } = await post.delete(id);
    if (data || status) {
      commit(symbols.cms.post.del, id);
    }
  },
};

export default { state, getters, mutations, actions };
