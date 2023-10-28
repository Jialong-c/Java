<template>
  <div>
    <ul>
      <li v-for="m in messageList" :key="m.id">
        <!---跳转路由并携带params参数，to的字符串写法-->
        <!--<router-link :to="`/home/message/detail/${m.id}/${m.title}`">{{m.title}}</router-link>&nbsp;&nbsp;-->

        <!---跳转路由并携带params参数，to的对象写法-->
        <router-link :to="{
          // path:'/home/message/detail',
          // 使用params携带参数必须使用name，不能使用path
          name:'xiangqing',
          query:{
            id:m.id,
            title:m.title
          }
        }">
          {{m.title}}
        </router-link>&nbsp;&nbsp;
        <button @click="pushShow(m)">push查看</button>
        <button @click="replaceShow(m)">replace查看</button>
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
    },
    pushShow(m){
      this.$router.push({
        name:'xiangqing',
        query:{
          id:m.id,
          title:m.title
        }
      })
    },
    replaceShow(m){
      this.$router.replace({
        name:'xiangqing',
        query:{
          id:m.id,
          title:m.title
        }
      })
    }
  },
  mounted() {
    this.getMessage()
  }
}
</script>