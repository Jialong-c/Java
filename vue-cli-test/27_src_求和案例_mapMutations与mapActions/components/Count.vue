<template>
  <div>
    <h1>当前求和为:{{sum}}</h1>
    <h3>当前求和放大10倍为:{{bigSum}}</h3>
    <h3>我在{{school}}学习{{subject}}</h3>
    <!--让其收集到的数据全是number类型的 number修饰符-->
    <select v-model.number="n">
      <!--让所有的value全部绑定为数字-->
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <button @click="increment(n)">+</button>
    <button @click="decrement(n)">-</button>
    <button @click="incrementIfOdd(n)">当前求和为奇数再加</button>
    <button @click="incrementWait(n)">等一等再加</button>
  </div>
</template>

<script>
  //mapActions与mapMutations使用时，若需要传递参数需要：在模板中绑定事件时传递好参数,否则参数是事件对象。
  import {mapState,mapGetters,mapActions,mapMutations} from 'vuex'
  export default {
    name:'Count',
    data() {
      return {
        n: 1,// 用户在select框选择的数字
        //sum: 0 //当前的和
      }
    },
    methods:{
      //action中increment没有判断可以直接commit
      /*increment(){
        this.$store.commit('INCREMENT',this.n)
      },
      decrement(){
        this.$store.commit('DECREMENT',this.n)
      },*/
      //借助mapMutations生成对应方法，方法会调用commit去联系mutations（对象写法）
      ...mapMutations({increment: 'INCREMENT',decrement:'DECREMENT'}),

      /*incrementOdd(){
        this.$store.dispatch('incrementIfOdd',this.n)
      },
      incrementWait(){
        this.$store.dispatch('incrementWait',this.n)
      }*/
      //借助mapActions生成对应方法，方法会调用dispatch去联系actions（数组写法）
      ...mapActions(['incrementIfOdd','incrementWait'])
    },
    computed:{
      //借助mapState生成计算属性，从state中读取数据。（对象写法）“...”将生成结果（对象）添加到computed中
      // ...mapState({sum:'sum',school: 'school',subject:'subject'}),

      //不能写成sum:sum，属性名：变量名，没有sum变量
      //借助mapState生成计算属性，从state中读取数据。（数组写法）
      ...mapState(['sum','school','subject']),

      ...mapGetters(['bigSum'])
    }
  }
</script>

<style scoped>
  button{
    margin-left: 5px;
  }
</style>