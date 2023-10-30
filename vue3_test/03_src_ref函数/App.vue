<template>
  <h1>个人信息</h1>
  <h2>姓名：{{name}}</h2>
  <h2>年龄：{{age}}</h2>
  <h3>职位:{{ job.type }}</h3>
  <h3>薪水:{{ job.salary }}</h3>
  <button @click="changeInfo">修改人的信息</button>
</template>

<script>
  import {ref} from "vue";
  export default {
    name: 'App',
    setup(){
      //数据
      let name=ref('张三')
      let age=ref(18)
      let job = ref({
        type: '前端',
        salary: 30
      });
      //console.log(name)

      //方法
      function changeInfo(){
        name.value='李四'
        age.value=48
        console.log(job.value)
        job.value.type='后端'
        job.value.salary=31
      }

      return{name,age,changeInfo,job}
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
