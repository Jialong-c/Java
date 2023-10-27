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
    <button @click="increment">+</button>
    <button @click="decrement">-</button>
    <button @click="incrementOdd">当前求和为奇数再加</button>
    <button @click="incrementWait">等一等再加</button>
  </div>
</template>

<script>
  import {mapState,mapGetters} from 'vuex'
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
      increment(){
        this.$store.commit('INCREMENT',this.n)
      },
      decrement(){
        this.$store.commit('DECREMENT',this.n)
      },
      incrementOdd(){
        this.$store.dispatch('incrementIfOdd',this.n)
      },
      incrementWait(){
        this.$store.dispatch('incrementWait',this.n)
      }
    },
    computed:{
      /*sum(){
        return this.$store.state.sum
      },*/
      //借助mapState生成计算属性，从state中读取数据。（对象写法）“...”将生成结果（对象）添加到computed中
      // ...mapState({sum:'sum',school: 'school',subject:'subject'}),

      //不能写成sum:sum，属性名：变量名，没有sum变量
      //借助mapState生成计算属性，从state中读取数据。（数组写法）
      ...mapState(['sum','school','subject']),
      
      /*bigSum(){
        return this.$store.getters.bigSum
      }*/
      ...mapGetters(['bigSum'])
    }
  }
</script>

<style scoped>
  button{
    margin-left: 5px;
  }
</style>