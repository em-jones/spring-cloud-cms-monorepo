import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/Home';
import Editor from '@/components/common/Editor';
import Archive from '@/components/Archive';
import About from '@/components/About';
import Tags from '@/components/Tags';
import Post from '@/components/Post';
import Login from '@/components/Login';
import Content from '@/components/Content';
import ContentList from '@/components/ContentList';

import { Auth } from '@/utils/AuthService';

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/archive',
      name: 'archive',
      component: Archive,
    },
    {
      path: '/about',
      name: 'about',
      component: About,
    },
    {
      path: '/tags',
      name: 'tags',
      component: Tags,
    },
    {
      path: '/post/create',
      name: 'create',
      component: Editor,
      beforeEnter: (from, to, next) => {
        if (Auth.isAuthenticated()) {
          next();
        } else {
          next('/');
        }
      },
    },
    {
      path: '/post/:id',
      name: 'post',
      component: Post,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/auth',
      name: 'auth',
      component: Auth,
    },
    {
      path: '/tag',
      name: 'tag-filter',
      component: Archive,
    },
    {
      path: '/logout',
      beforeEnter: (from, to, next) => {
        Auth.logout();
        next('/');
      },
    },
    {
      path: '/content/list',
      component: ContentList,
      beforeEnter: (from, to, next) => {
        if (Auth.isAuthenticated()) {
          next();
        } else {
          next('/');
        }
      },
    },
    {
      path: '/content/:id',
      component: Content,
    },
    {
      path: '/content',
      component: Content,
    },
  ],
});
