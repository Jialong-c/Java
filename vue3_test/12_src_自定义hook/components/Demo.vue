<template>
  <h2>当前求和为：{{sum}}</h2>
  <button @click="sum++">点我+1</button>
  <hr/>
  <h2>当前点击时鼠标的坐标为x:{{ point.x }}, y:{{ point.y }}</h2>
</template>

<script>
import {onBeforeUnmount, onMounted, reactive, ref} from "vue";
import UsePoint from "@/hooks/usePoint";
  export default {
    name: 'Demo',
    setup(){
      let sum=ref(0)
      let point=UsePoint()

      return{sum,point}
    },
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
