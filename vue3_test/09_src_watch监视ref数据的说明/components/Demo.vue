<template>
  <h2>当前求和为：{{sum}}</h2>
  <button @click="sum++">点我+1</button>
  <h2>当前信息为：{{msg}}</h2>
  <button @click="msg+='!'">点我+！</button>
  <hr/>
  <h2>姓名:{{ person.name }}</h2>
  <h2>年龄:{{ person.age }}</h2>
  <h2>薪资:{{ person.job.j1.salary }}</h2>
  <button @click="person.name = person.name + '~'">修改姓名</button>
  <button @click="person.age++">增长年龄</button>
  <button @click="person.job.j1.salary++">增长薪资</button>
</template>

<script>
import {ref,watch,reactive} from "vue";
  export default {
    name: 'Demo',
    setup(){
      let sum=ref(0)
      let msg=ref('hello')
      let person=ref({
        name:'张三',
        age:18,
        job:{
          j1:{
            salary: 20
          }
        }
      })

      //监测的不是一个值，而是一个保存值的结构(不能写成sum.value，否则相当于监视0)
      watch(sum,(value,oldValue)=>{
        console.log('sum值变了',value,oldValue)
      })

      //这里如果不是person.value则会出现问题 因为person是一个RefImpl(默认没开启深度监视)
      //但是person.value是一个借助了proxy生成的reactive响应式对象 所以这里可以解决问题
      //person是一个RefImpl(默认没开启深度监视)，不开启深度监视无法获取name
      watch(person,(value,oldValue)=>{
        console.log('person值变了',value,oldValue)
      },{deep:true})

      return{sum,msg,person}
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
