export default {
  initcap(word) {
    return word.toLowerCase().replace(/(?:^|\s)[a-z]/g, m => m.toUpperCase());
  },
};
