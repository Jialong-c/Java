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
      let person=reactive({
        name:'张三',
        age:18,
        job:{
          j1:{
            salary: 20
          }
        }
      })

      //情况一：监视ref定义的响应式数据
      /*watch(sum,(value, oldValue) => {
        console.log('sum变了',value,oldValue)
      },{immediate:true})*/

      //情况二：监视ref定义的多个响应式数据
      /*watch([sum,msg],(value, oldValue) => {
        console.log('sum或msg变了',value,oldValue)
      })*/

      //情况三：监视reactive定义的响应式数据,无法获取正确的oldValue,强制开启了深度监视
      /*watch(person,(value, oldValue) => {
        console.log('person变了',value,oldValue)
      })*/

      //情况四：监视reactive所定义的响应式中的某一个属性
      /*watch(()=>person.age,(value, oldValue) => {
        console.log('person变了',value,oldValue)
      })*/

      //情况五:监视reactive所定义的响应式中的某些属性:并不只是一个
      /*watch([() => person.age, () => person.name], (value, oldValue) => {
        //此时nv和ov都是数组
        console.log('person的age或name变了',value, oldValue);
      });*/

      //特殊情况
      watch(() => person.job, (value, oldValue) => {
        //这里依然无法拿到正确的ov，因为依然监视的是对象
        console.log('person的job信息发生改变了',value, oldValue);
      }, {
        //这里必须要加deep:true注意
        deep: true //此处因为监视的是reactive所定义的响应式对象的一个属性(这个属性的值它依然是一个对象)，所以deep配置有效
      })

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
