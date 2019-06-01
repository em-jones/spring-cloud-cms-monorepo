/* eslint-disable import/no-dynamic-require,global-require */
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import hljs from 'highlight.js/lib/highlight';
import Vue from 'vue';
import VueMoment from 'vue-moment';
import VueLocalStorage from 'vue-localstorage';
import App from './App';
import router from './router';
import AuthService from './utils/AuthService';
import store from './store';

['javascript', 'python', 'java'].forEach((langName) => {
  // Using require() here because import() support hasn't landed in Webpack yet
  const langModule = require(`highlight.js/lib/languages/${langName}`);
  hljs.registerLanguage(langName, langModule);
});
window.hljs = hljs;

Vue.use(AuthService);
Vue.use(VueMoment);
Vue.use(VueLocalStorage);
Vue.config.productionTip = false;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { App },
  router,
  template: '<App/>',
  store,
});
