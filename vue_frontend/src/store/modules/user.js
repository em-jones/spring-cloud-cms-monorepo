import Vue from 'vue';
import moment from 'moment';
import { auth } from '@/api';
import symbols from '../symbols';

const state = {
  user: {
    username: '',
    email: '',
  },
  tokens: {
    access_token: '',
    refresh_token: '',
    expires: '',
  },
};

const getters = {
  [symbols.user.load]: s => s.user,
};

const mutations = {
  [symbols.user.init]: (s, { username, email }) => {
    Vue.set(s.user, 'username', username);
    Vue.set(s.user, 'email', email);
  },
  // eslint-disable-next-line camelcase
  [symbols.user.token]: (s, { access_token, refresh_token, expires }) => {
    Vue.set(s.tokens, 'access_token', access_token);
    Vue.set(s.tokens, 'refresh_token', refresh_token);
    Vue.set(s.tokens, 'expires', moment().add(expires, 'seconds'));
  },
};

const actions = {
  async [symbols.user.login]({ commit }, { username, password }) {
    const tokens = await auth.login(username, password);
    const user = await auth.user(tokens);
    commit(symbols.user.login, { tokens, user });
  },
  async [symbols.user.refresh]({ commit }, refreshToken) {
    const tokens = await auth.refresh(refreshToken);
    const user = await auth.user(tokens);
    commit(symbols.user.login, { tokens, user });
  },
  [symbols.user.init]: ({ commit }, user) => {
    commit(symbols.user.init, user);
  },
};

export default { state, getters, mutations, actions };
