<template>
	<div class="app">
		<h1>{{msg}}</h1>
		<School/>
    <Student/>
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
      }
    },
		components:{School,Student}
  }
</script>

<style scoped>
  .app{
    background-color: grey;
    padding: 5px;
  }
</style>
