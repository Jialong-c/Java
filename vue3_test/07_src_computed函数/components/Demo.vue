<template>
  <h1>个人信息</h1>
  姓：<input type="text" v-model="person.firstName">
  <br/>
  名：<input type="text" v-model="person.lastName">
  <br/>
  <span>全名：{{person.fullName}}</span>
  <br/>
  全名：<input type="text" v-model="person.fullName">
</template>

<script>
  import {reactive,computed} from "vue";
  export default {
    name: 'Demo',
    setup(){
      let person=reactive({
        firstName:'张',
        lastName:'三',
      })

      //计算属性(简写，没有考虑计算属性被修改的情况)
      /*person.fullName=computed(()=>{
        return person.firstName+'-'+person.lastName
      })*/

      person.fullName=computed({
        get(){
          return person.firstName+'-'+person.lastName
        },
        set(value){
          const nameArr=value.split('-')
          person.firstName=nameArr[0]
          person.lastName=nameArr[1]
        }
      })

      return{person}
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
