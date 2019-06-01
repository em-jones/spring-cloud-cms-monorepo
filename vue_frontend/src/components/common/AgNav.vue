<template>
  <nav class="main-nav">
    <router-link to="/" v-if="$route.path !== '/'"><span class="arrow">←</span>Home</router-link>
    <router-link to="/archive">Archive</router-link>
    <router-link to="/tags">Tags</router-link>
    <router-link to="/about">About</router-link>
    <a class="pointer" v-if="isEm && !loggedIn" @click="login">Login</a>
    <a class="pointer" v-else-if="isEm" @click="logout">Logout</a>
    <!--<a class="cta" href="https://www.agiantagravic.com/index.xml">Subscribe</a>-->
  </nav>
</template>

<script>

export default {
  computed: {
    isEm() {
      return this.$route.query.em === 'true';
    },
    loggedIn() {
      return this.$auth.authenticated;
    },
  },
  methods: {
    login() {
      this.$auth.login();
    },
    logout() {
      this.$auth.logout();
    },
  },
  name: 'ag-nav',
};
</script>

<style scoped>

</style>
