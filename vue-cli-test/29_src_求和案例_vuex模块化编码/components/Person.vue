<template>
  <div>
    <h1>人员列表</h1>
    <h3>列表中第一个人的名字是：{{firstPersonName}}</h3>
    <input type="text" placeholder="请输入名字" v-model="name" @keyup.enter="add">
    <button @click="add" >添加</button>
    <button @click="addWang">添加一个姓王的人</button>
    <button @click="addPersonServer">添加后端发送的内容</button>
    <ul>
      <li v-for="person in personList" :key="person.id">{{person.name}}</li>
    </ul>
  </div>
</template>

<script>
  import {mapState} from "vuex";
  import {nanoid} from "nanoid";

  export default {
    name:'Person',
    data() {
      return {
        name: ''
      }
    },
    computed:{
      ...mapState('person',['personList']),
      firstPersonName(){
        return this.$store.getters["person/firstPersonName"]
      }
    },
    methods:{
      add(){
        const personObj={id:nanoid(),name:this.name}
        this.$store.commit('person/ADD_PERSON',personObj)
        this.name=''
      },
      addWang(){
        const personObj={id:nanoid(),name:this.name}
        this.$store.dispatch('person/addPersonWang',personObj)
        this.name=''
      },
      addPersonServer(){
        this.$store.dispatch('person/addPersonServer')
      }
    }
  }
</script>

<style scoped>

</style>