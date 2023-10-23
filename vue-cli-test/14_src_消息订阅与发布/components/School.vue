<template>
	<div class="demo">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
	</div>
</template>

<script>
  import pubsub from 'pubsub-js'
	export default {
		name:'School',
		data() {
			return {
				name:'XD',
				address:'西安',
			}
		},
    mounted() {
      /*this.$bus.$on('hello',(data)=>{
        console.log('School组件收到数据',data)
      })*/
      this.pubId=pubsub.subscribe('hello',(msgName,data)=>{
        console.log('有人发布了hello消息，回调执行',msgName,data)
      })
    },
    beforeDestroy() {
      //this.$bus.$off('hello')
      pubsub.unsubscribe(this.pubId)
    }
  }
</script>

<style scoped>
	.demo{
		background-color: skyblue;
    padding: 5px;
	}
</style>