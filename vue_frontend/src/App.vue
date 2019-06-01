<template>
  <div id="app">
    <ag-nav></ag-nav>
    <ag-base>
      <router-view></router-view>
    </ag-base>
    <ag-footer></ag-footer>
  </div>
</template>

<script>
import AgFooter from '@/components/common/AgFooter';
import AgNav from '@/components/common/AgNav';
import AgBase from '@/components/AgBase';

export default {
  name: 'App',
  components: {
    AgNav, AgFooter, AgBase,
  },
};
</script>
<style lang="sass" src="./assets/sass/app.scss" ></style>
