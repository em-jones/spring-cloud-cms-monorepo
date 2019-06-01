<template>
  <router-link to='/about'>
    <img id="avatar" class="2x" :src="resource"/>
  </router-link>
</template>
<script>
export default {
  name: 'ag-avatar',
  props: {
    resource: {
      default: '/static/images/avatar.svg',
    },
    alt: {
      default: 'Agravic Logo',
    },
  },
};
</script>
