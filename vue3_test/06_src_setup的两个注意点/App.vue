<template>
  <Demo @hello="sayHello" msg="hello" school="XD">
    <template v-slot:test>
      <span>123</span>
    </template>
  </Demo>
</template>

<script>
  import Demo from "@/components/Demo.vue";
  export default {
    name: 'App',
    components: {Demo},
    setup(){
      function sayHello(value){
        alert(`hello事件触发，收到参数：${value}`)
      }

      return{sayHello}
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
