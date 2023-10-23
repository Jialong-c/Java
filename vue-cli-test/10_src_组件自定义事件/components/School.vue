<template>
	<div class="demo">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
    <button @click="sendSchoolName">把学校名给APP</button>
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
    props:['getSchoolName'],
    methods:{
      sendSchoolName(){
        this.getSchoolName(this.name)
      }
    }
	}
</script>

<style scoped>
	.demo{
		background-color: skyblue;
    padding: 5px;
	}
</style>