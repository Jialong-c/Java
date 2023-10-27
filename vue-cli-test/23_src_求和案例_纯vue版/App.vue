<template>
  <div>
    <Count/>
  </div>
</template>

<!--跨域是浏览器行为，不是服务器行为-->
<script>
  import Count from "./components/Count.vue";
  export default {
    name:'App',
    components: {Count}
  }
</script>

<style>

</style>