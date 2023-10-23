<template>
	<div class="app">
		<h1>{{msg}}，学生姓名：{{studentName}}</h1>
		<School :getSchoolName="getSchoolName"/>
    <!--通过父组件给子组件绑定一个自定义事实现：子给父传递数据(自定义事件绑在子组件上) 第一种写法使用@或v-on-->
<!--    <Student v-on:getStudentName="getStudentName" @demo="m1"/>-->
    <!--通过父组件给子组件绑定一个自定义事实现：子给父传递数据(自定义事件绑在子组件上) 第二种写法使用ref-->
    <Student ref="student" @click.native="show"/>
	</div>
</template>

<script>
	import Student from './components/Student'
	import School from './components/School'

	export default {
		name:'App',
    data() {
      return {
        msg: 'hello',
        studentName:''
      }
    },
		components:{School,Student},
    methods:{
      getSchoolName(name){
        console.log('APP收到了学校名',name)
      },
      getStudentName(name,...param){
        console.log('demo被调用',name,param)
        this.studentName=name
      },
      m1(){
        console.log('demo事件被触发')
      },
      show(){
        alert(123)
      }
    },
    /*mounted() {
      setInterval(()=>{
        // this.$refs.student.$on('getStudentName',this.getStudentName)//绑定自定义事件
        //this.$refs.student.$once('getStudentName',this.getStudentName)//绑定自定义事件（一次性）
      },3000)
    }*/
  }
</script>

<style scoped>
  .app{
    background-color: grey;
    padding: 5px;
  }
</style>
