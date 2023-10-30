<template>
  <h1>个人信息</h1>
  <h2>姓名：{{name}}</h2>
  <h2>年龄：{{age}}</h2>
  <h3>职位:{{ job.type }}</h3>
  <h3>薪水:{{ job.salary }}</h3>
  <h3>爱好：{{hobby}}</h3>
  <button @click="changeInfo">修改人的信息</button>
</template>

<script>
  import {ref,reactive} from "vue";
  export default {
    name: 'App',
    setup(){
      //数据
      let name=ref('张三')
      let age=ref(18)
      let job = reactive({
        type: '前端',
        salary: 30
      });
      let hobby=['抽烟','喝酒']
      //console.log(name)

      //方法
      function changeInfo(){
        name.value='李四'
        age.value=48
        console.log(job)
        job.type='后端'
        job.salary=31
        hobby[0]='学习'
      }

      return{name,age,changeInfo,job,hobby}
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
