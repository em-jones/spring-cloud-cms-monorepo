export default {
  computed: {
    loggedIn() {
      return this.$auth.authenticated;
    },
  },
};

