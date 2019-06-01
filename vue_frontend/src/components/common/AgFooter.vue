<template>
  <section class="home wrapper">


    <nav id="post-nav">

    </nav>
    <footer id="footer">

      <div id="social">


        <a class="symbol" href="https://gitlab.com/emjones">
          <font-awesome-icon :icon="gitlab"></font-awesome-icon>
        </a>

        <a class="symbol" href="https://www.linkedin.com/in/emgjones">
          <font-awesome-icon :icon="linkedin"></font-awesome-icon>
        </a>

        <a class="symbol" href="https://twitter.com/mgjoneskc">
          <font-awesome-icon :icon="twitter"></font-awesome-icon>
        </a>
      </div>

      <p class="small">

       © Copyright 2017 <font-awesome-icon :icon="heart"></font-awesome-icon> A Giant Agravic

    </p>
    </footer>

  </section>

</template>

<script>
import FontAwesomeIcon from '@fortawesome/vue-fontawesome';
import faGitlab from '@fortawesome/fontawesome-free-brands/faGitlab';
import faLinkedin from '@fortawesome/fontawesome-free-brands/faLinkedin';
import faTwitter from '@fortawesome/fontawesome-free-brands/faTwitter';
import faHeart from '@fortawesome/fontawesome-free-solid/faHeart';

export default {
  components: {
    FontAwesomeIcon,
    faGitlab,
    faLinkedin,
    faTwitter,
  },
  computed: {
    heart() {
      return faHeart;
    },
    linkedin() {
      return faLinkedin;
    },
    twitter() {
      return faTwitter;
    },
    gitlab() {
      return faGitlab;
    },
  },
  name: 'ag-footer',
};
</script>

<style scoped>

</style>
