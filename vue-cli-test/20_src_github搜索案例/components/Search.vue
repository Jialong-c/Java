<template>
  <section class="jumbotron">
    <h3 class="jumbotron-heading">Search Github Users</h3>
    <div>
      <input type="text" placeholder="enter the name you search" v-model="keyWord" @keyup.enter="searchUsers"/>&nbsp;
      <button @click="searchUsers">Search</button>
    </div>
  </section>
</template>

<script>
  import axios from "axios";
  export default {
    name:'Search',
    data() {
      return {
        keyWord: ''
      }
    },
    methods:{
      searchUsers(){
        this.$bus.$emit('updateListData',{isFirst:false,isLoading:true,users:[],errMsg:''})
        //模板字符串
        axios.get(`https://api.github.com/search/users?q=${this.keyWord}`).then(
            response =>{
              //console.log('请求成功',response.data.items)
              //this.$bus.$emit('userList',response.data.items)
              this.$bus.$emit('updateListData',{isLoading:false,users:response.data.items,errMsg:''})
            },
            error =>{
              console.log('请求失败',error.message)
              this.$bus.$emit('updateListData',{isLoading:false,users:[],errMsg:error.message})
            }
        )
      }
    }
  }
</script>

<style scoped>

</style>