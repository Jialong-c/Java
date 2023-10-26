<template>
  <div class="container">
    <Search/>
    <List/>
  </div>
</template>

<!--跨域是浏览器行为，不是服务器行为-->
<script>
  import axios from "axios";
  import Search from "./components/Search.vue";
  import List from "./components/List.vue";
  export default {
    name:'App',
    components: {List, Search},
  }
</script>

<style>

</style>