<template>
	<div class="demo">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
	</div>
</template>

<script>
	export default {
		name:'School',
		data() {
			return {
				name:'XD',
				address:'西安',
			}
		},
    mounted() {
      this.$bus.$on('hello',(data)=>{
        console.log('School组件收到数据',data)
      })
    },
    beforeDestroy() {
      this.$bus.$off('hello')
    }
  }
</script>

<style scoped>
	.demo{
		background-color: skyblue;
    padding: 5px;
	}
</style>