<template>
  <div class="container">
    <Category title="美食">
      <img slot="center" src="" alt="">
      <a href="" slot="footer">更多美食</a>
    </Category>
    <Category title="游戏">
      <ul slot="center">
        <li v-for="(game,index) in games" :key="index">
          {{game}}
        </li>
      </ul>
      <div class="foot" slot="footer">
        <a href="" slot="footer">单机游戏</a>
        <a href="" slot="footer">网络游戏</a>
      </div>
    </Category>
    <Category title="电影">
      <video slot="center" src=""></video>
      <template v-slot:footer>
        <div class="foot">
          <a href="" slot="footer">经典</a>
          <a href="" slot="footer">热门</a>
          <a href="" slot="footer">推荐</a>
        </div>
        <h4>欢迎前来观影</h4>
      </template>

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
  .container,.foot{
    display: flex;
    justify-content: space-around;
  }
  video {
    width: 100%;
  }
  img{
    width: 100%;
  }
  h4{
    text-align: center;
    }
</style>