<template>
  <h2>当前求和为：{{sum}}</h2>
  <button @click="sum++">点我+1</button>
</template>

<script>
import {ref,onBeforeMount,onMounted,onBeforeUpdate,onUpdated,onBeforeUnmount,onUnmounted} from "vue";
  export default {
    name: 'Demo',
    setup(){
      let sum=ref(0)

      //通过组合式api的形式去使用生命周期钩子
      onBeforeMount(() => {  console.log('----beforeMount----'); });
      onMounted(() => {  console.log('-----mounted-----'); });
      onBeforeUpdate(() => {  console.log('-----beforeUpdate-----') });
      onUpdated(() => { console.log('-----updated-----'); });
      onBeforeUnmount(() => { console.log('-----beforeUnmount----'); });
      onUnmounted(() => { console.log('-----unmounted----'); })
      return{sum}
    },
    //使用配置项的形式使用生命周期钩子
    /*beforeCreate() {
      console.log('----beforeCreate!!----');
    },
    created() {
      console.log('----created!!----');
    },
    beforeMount() {
      console.log('----beforeMount----');
    },
    mounted() {
      console.log('-----mounted-----');
    },
    beforeUpdate() {
      console.log('-----beforeUpdate-----')
    },
    updated() {
      console.log('-----updated-----');
    },
    beforeUnmount() {
      console.log('-----beforeUnmount----');
    },
    unmounted() {
      console.log('-----unmounted----');
    }*/
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
