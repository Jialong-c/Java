<template>
  <div class="container">
    <Category title="美食">
      <img src="" alt="">
    </Category>
    <Category title="游戏">
      <ul>
        <li v-for="(game,index) in games" :key="index">
          {{game}}
        </li>
      </ul>
    </Category>
    <Category title="电影">
      <video src=""></video>
    </Category>
  </div>
</template>

<!--跨域是浏览器行为，不是服务器行为-->
<script>
  import Category from "./components/Category.vue";
  export default {
    name:'App',
    components: {Category},
    data(){
      return {
        foods:['火锅','烧烤','小龙虾','牛排'],
        games:['红色警戒','穿越火线','劲舞团','超级玛丽'],
        films:['《教父》','《拆弹专家》','《你好，李焕英》','《尚硅谷》']
      }
    }
  }
</script>

<style>
  .container{
    display: flex;
    justify-content: space-around;
  }
  video {
    width: 100%;
  }
  img{
    width: 100%;
  }
</style>