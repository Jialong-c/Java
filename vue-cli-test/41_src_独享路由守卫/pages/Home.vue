<template>
  <div>
    <h2>Home组件内容</h2>
    <div>
      <ul class="nav nav-tabs">
        <li>
          <router-link class="list-group-item" active-class="active" to="/home/news">News</router-link>
        </li>
        <li>
          <router-link class="list-group-item" active-class="active" to="/home/message">Message</router-link>
        </li>
      </ul>
      <!--include中填写组件名，包含的组件不会被销毁-->
      <keep-alive include="">
        <router-view/>
      </keep-alive>
    </div>
  </div>
</template>

<script>
  export default {
    name:'Home',
    /*beforeDestroy() {
      console.log('Home组件即将被销毁')
    },
    mounted() {
      console.log('Home组件挂载完毕')
    }*/
  }
</script>