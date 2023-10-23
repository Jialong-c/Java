<template>
	<div class="student">
		<h2>学生姓名：{{name}}</h2>
		<h2>学生性别：{{sex}}</h2>
    <h2>当前求和为:{{ number }}</h2>
    <button @click="add">点我加一</button>
    <button @click="sendStudentName">把学生名给APP</button>
    <button @click="unbind">解绑getStudentName事件</button>
    <button @click="death">销毁当前student组件的实例对象</button>
	</div>
</template>

<script>
	export default {
		name:'Student',
		data() {
			return {
				name:'张三',
				sex:'男',
        number:0
			}
		},
    methods:{
      add(){
        console.log(`add回调被调用了`);
        this.number ++;
      },
      sendStudentName(){
        //触发Student组件实例上的getStudentName事件
        this.$emit('getStudentName',this.name)
        //this.$emit('demo')
      },
      unbind() {
        this.$off('getStudentName')//解绑一个自定义事件
        //this.$off(['getStudentName','demo'])//解绑多个自定义事件
        //this.$off()//解绑所有自定义事件
      },
      death(){
        this.$destroy()//销毁当前组件实例，销毁后所有该实例的自定义事件都不奏效了
      }
    }
	}
</script>

<style lang="less" scoped>
	.student{
		background-color: aquamarine;
    padding: 5px;
    margin-top: 30px;
	}
</style>
