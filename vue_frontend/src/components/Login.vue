<template>
  <article class="mw5 center bg-white br3 pa3 pa4-ns mv3 ba b--black-10">
    <div class="tc">
      <img src="http://tachyons.io/img/avatar_1.jpg" class="br-100 h3 w3 dib" title="Photo of a kitty staring at you">
      <h1 class="f4">Get The Party Going</h1>
      <hr class="mw3 bb bw1 b--black-10">
      <form class="pa4 black-80" :class="{ 'bg-light-red': formError}">
        <div class="measure">
          <label for="name" class="f6 b db mb2">Username</label>
          <input v-model="username" id="name"
                 class="input-reset ba b--black-20 pa2 mb2 db w-100"
                 type="text" aria-describedby="name-desc">
          <small id="name-desc" class="f6 black-60 db mb2">
            Helper text for the form control.</small>
        </div>
        <div class="measure">
          <label for="name" class="f6 b db mb2">Password</label>
          <input v-model="password" id="password"
                 class="input-reset ba b--black-20 pa2 mb2 db w-100"
                 type="password" aria-describedby="password-desc">
          <small id="password-desc" class="f6 black-60 db mb2">
            Helper text for the form control.</small>
        </div>
      </form>
    </div>
    <footer class="pv4 ph2 tc-l inline-flex items-center w-100">
      <a class="no-underline bg-animate
      hover-bg-gray  ma2 tc br2 pa2 bg-light-purple
      flex-grow-1 near-white" @click="login">Login</a>
    </footer>
  </article>
</template>

<script>
import AgBase from '@/components/AgBase';

export default {
  computed: {
    formError() {
      return !this.loginSuccess;
    },
  },
  data() {
    return {
      username: '',
      password: '',
      loginSuccess: true,
    };
  },
  methods: {
    async login() {
      if (await this.$auth.login(this)) {
        this.loginSuccess = true;
        this.$router.push('/');
      } else {
        this.loginSuccess = false;
        this.username = '';
        this.password = '';
      }
    },
  },
  mounted() {
  },
  name: 'login',
  components: {
    AgBase,
  },
};
</script>

<style scoped>

</style>
