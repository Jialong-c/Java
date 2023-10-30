<template>
  <button @click="isShow=!isShow">点我显示/隐藏组件</button>
  <Demo v-if="isShow"/>
</template>

<script>
  import Demo from "@/components/Demo.vue";
  import {ref} from "vue";
  export default {
    name: 'App',
    components: {Demo},
    setup(){
      let isShow=ref(true)

      return {isShow}
    }
  }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
