<template>
  <h1>个人信息</h1>
  <h2>姓名：{{name}}</h2>
  <h2>年龄：{{age}}</h2>
  <h2>性别：{{sex}}</h2>
  <h2>a的值是：{{a}}</h2>
  <button @click="sayHello">说话</button>
  <button @click="sayWelcome">欢迎(Vue2)</button>
  <button @click="test1">测试一下在vue2中去读取vue3的配置</button>
  <button @click="test2">测试一下在vue3的setup中去读取vue2的配置</button>
</template>

<script>
  export default {
    name: 'App',
    data(){
      return{
        sex:'男',
        a:2
      }
    },
    methods:{
      sayWelcome(){
        alert('Welcome!')
      },
      test1(){
        console.log(this.sex);
        console.log(this.name);
        console.log(this.sayHello);
        this.sayHello();
      }
    },
    //此处只是测试setup，暂时不考虑响应式的问题
    setup(){
      //数据
      let name='张三'
      let age=18
      let a=3

      //方法
      function sayHello(){
        alert(`姓名：${name}，年龄：${age}`)
      }

      function test2(){
        console.log(name);
        console.log(sayHello);
        console.log(sex);
        this.sayHello();
      }

      return{name,age,sayHello,test2,a}

      //返回一个渲染函数
      //渲染函数中的内容：'h1', 'hello' 直接替换到template中
      // return () => h('h1', 'hello');
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
