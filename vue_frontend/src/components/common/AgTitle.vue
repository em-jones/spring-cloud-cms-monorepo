<template>
  <transition>
    <h1>{{ value }}</h1>
  </transition>
</template>
<script>
export default {
  name: 'ag-title',
  props: {
    value: { default: 'A Giant Agravic' },
  },
};
</script>
