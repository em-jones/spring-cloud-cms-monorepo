<template>
  <div class="mt3">
    <label class="db fw6 lh-copy f6" :for="name">{{ label }}</label>
    <textarea v-if="textarea"
      class="pa2 input-reset ba bg-transparent hover-bg-black hover-white w-100"
        :name="name" :id="id" @keyup="change" :value="value"></textarea>
    <input v-else
      class="pa2 input-reset ba bg-transparent hover-bg-black hover-white w-100"
      :type="type" :name="name"  :id="id" @keyup="change" :value="value">
  </div>
</template>
<script>
export default {
  methods: {
    change(e) {
      this.$emit('updated', e);
    },
  },
  name: 'ag-input',
  props: ['type', 'name', 'id', 'label', 'textarea', 'value'],
};
</script>
