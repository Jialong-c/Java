<template>
  <div>
    <Count/>
    <hr/>
    <Person/>
  </div>
</template>

<!--跨域是浏览器行为，不是服务器行为-->
<script>
  import Count from "./components/Count.vue";
  import Person from "@/components/Person.vue";
  export default {
    name:'App',
    components: {Person, Count}
  }
</script>

<style>

</style>