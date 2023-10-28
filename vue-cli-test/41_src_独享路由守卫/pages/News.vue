<template>
  <div>
    <ul>
      <li :style="{opacity}">欢迎学习Vue</li>
      <li>news001<input></li>
      <li>news002<input></li>
      <li>news003<input></li>
    </ul>
  </div>
</template>

<script>
  export default {
    name:'News',
    data(){
      return{
        opacity:1
      }
    },
   /* beforeDestroy() {
      console.log('News组件即将被销毁')
      clearInterval(this.timer)
    },
    mounted() {
      this.timer=setInterval(()=>{
        this.opacity-=0.01
        if(this.opacity<=0){
          this.opacity=1
        }
      },16)
    },*/
    activated() {
      console.log('News组件被激活')
      this.timer=setInterval(()=>{
        console.log('@')
        this.opacity-=0.01
        if(this.opacity<=0){
          this.opacity=1
        }
      },16)
    },
    deactivated() {
      console.log('News组件失活')
      clearInterval(this.timer)
    }
  }
</script>

<style scoped>

</style>