/* eslint-disable no-shadow,consistent-return,import/first,no-return-await */
import Vue from 'vue';
import symbols from '../symbols';
import { content } from '../../api';

const state = {
  list: [],
  content: {},
  loaded: false,
};

const getters = {
  [symbols.cms.content.list]: s => s.list,
  [symbols.cms.content.view]: s => s.content,
  [symbols.cms.content.loaded]: s => s.loaded,
};

const mutations = {
  [symbols.cms.content.view]: (s, content) => {
    Vue.set(s, 'content', content);
    Vue.set(s, 'loaded', true);
  },
  [symbols.cms.content.list](s, list) {
    list.forEach((item, index) => {
      Vue.set(s.list, index, item);
    });
  },
  [symbols.cms.content.paginate.nextPage]: (s) => {
    Vue.set(s, 'page', s.page + 1);
  },
  [symbols.cms.content.paginate.prevPage]: (s) => {
    Vue.set(s, 'page', s.page - 1);
  },
};

const actions = {
  async [symbols.cms.content.view]({ commit }, campaign) {
    const { data } = await content.get(campaign);
    commit(symbols.cms.content.view, data);
    return data;
  },
  async [symbols.cms.content.save](_, newContent) {
    if (newContent.id === '') {
      return await content.update(newContent);
    }
    return await content.create(newContent);
  },
  [symbols.cms.content.set]: ({ commit }, content) => {
    commit(symbols.cms.content.view, content);
  },
  async [symbols.cms.content.list]({ commit }) {
    const { data } = await content.list();
    commit(symbols.cms.content.list, data);
  },
};

export default { state, getters, mutations, actions };

