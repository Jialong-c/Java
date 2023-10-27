<template>
  <div>
    <h1>人员列表</h1>
    <input type="text" placeholder="请输入名字" v-model="name" @keyup.enter="add">
    <button @click="add" >添加</button>
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
      ...mapState(['personList'])
    },
    methods:{
      add(){
        const personObj={id:nanoid(),name:this.name}
        this.$store.commit('ADD_PERSON',personObj)
        this.name=''
      }
    }
  }
</script>

<style scoped>

</style>