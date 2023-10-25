<template>
  <div>
    <button @click="getStudents">获取学生信息</button>
    <button @click="getCars">获取汽车信息</button>
  </div>
</template>

<!--跨域是浏览器行为，不是服务器行为-->
<script>
  import axios from "axios";
  export default {
    name:'App',
    methods:{
      getStudents(){
        axios.get('http://localhost:8080/api/students').then(
            response =>{
              console.log('请求成功',response.data)
            },
            error =>{
              console.log('请求失败',error.message)
            }
        )
      },
      getCars(){
        axios.get('http://localhost:8080/demo/cars').then(
            response =>{
              console.log('请求成功',response.data)
            },
            error =>{
              console.log('请求失败',error.message)
            }
        )
      }
    }
  }
</script>

<style scoped>

</style>