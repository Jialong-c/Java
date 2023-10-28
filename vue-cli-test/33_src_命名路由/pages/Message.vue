<template>
  <div>
    <ul>
      <li v-for="m in messageList" :key="m.id">
        <!---跳转路由并携带query参数，to的字符串写法-->
        <!--<router-link :to="`/home/message/detail?id=${m.id}&title=${m.title}`">{{m.title}}</router-link>&nbsp;&nbsp;-->

        <!---跳转路由并携带query参数，to的对象写法-->
        <router-link :to="{
          // path:'/home/message/detail',
          name:'xiangqing',
          query:{
            id:m.id,
            title:m.title
          }
        }">
          {{m.title}}
        </router-link>&nbsp;&nbsp;
      </li>
    </ul>
    <hr/>
    <router-view/>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'Message',
  data() {
    return {
      messageList: [
        {id: '001',title:''},
        {id:'002',title: ''},
        {id:'003',title: ''}
      ]
    }
  },
  methods:{
    getMessage(){
      for(let i=0; i<this.messageList.length; i++){
        axios.get('https://api.apiopen.top/api/sentences').then(
            response=>{
              this.messageList[i].title=response.data.result.name+'--'+response.data.result.from
            },
            error=>{
              alert(error.message)
            }
        )
      }
    }
  },
  mounted() {
    this.getMessage()
  }
}
</script>