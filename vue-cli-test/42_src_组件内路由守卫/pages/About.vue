<template>
  <div>
    <h2>我是About的内容</h2>
  </div>
</template>

<script>
  export default {
    name:'About',
    /*beforeDestroy() {
      console.log('About组件即将被销毁')
    },
    mounted() {
      console.log('About组件挂载完毕')
    }*/

    //通过路由规则进入该组件时被调用
    beforeRouteEnter(to,from,next){
      console.log('beforeRouteEnter',to,from)
      if(to.meta.isAuth){//判断是否需要鉴权
        // if(to.path==='/home/news'||to.path==='/home/message'){
        if(sessionStorage.getItem('school')==='XD'){
          next()
        }else{
          alert('无权限')
        }
      }else{
        next()
      }
    },

    //通过路由规则离开该组件时被调用
    beforeRouteLeave(to,from,next){
      console.log('beforeRouteLeave',to,from)
      next()
    }
  }
</script>