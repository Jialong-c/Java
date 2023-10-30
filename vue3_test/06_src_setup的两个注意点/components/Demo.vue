<template>
  <h1>个人信息</h1>
  <h2>姓名：{{person.name}}</h2>
  <h2>年龄：{{person.age}}</h2>
  <button @click="test">测试hello事件</button>
</template>

<script>
  import {reactive} from "vue";
  export default {
    name: 'Demo',
    props:['msg','school'],
    emits:['hello'],
    beforeCreate() {
      console.log('beforeCreate')
    },
    setup(props,context){
      // console.log('setup',props,context)
      // console.log('setup',context.emit)
      console.log('setup',context.slots)
      let person=reactive({
        name:'张三',
        age:18,
      })

      function test(){
        context.emit('hello',666)
      }

      return{person,test}
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
