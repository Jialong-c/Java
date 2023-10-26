<template>
  <div class="container">
    <Category title="游戏">
      <template scope="data">
        <ul>
          <li v-for="(game,index) in data.games" :key="index">
            {{game}}
          </li>
        </ul>
      </template>
    </Category>

    <Category title="游戏">
      <template scope="data">
        <ol>
          <li v-for="(game,index) in data.games" :key="index">
            {{game}}
          </li>
        </ol>
      </template>
    </Category>

    <Category title="游戏">
      <template slot-scope="data">
        <h4 v-for="(game,index) in data.games" :key="index">{{game}}</h4>
      </template>
    </Category>
  </div>
</template>

<!--跨域是浏览器行为，不是服务器行为-->
<script>
  import Category from "./components/Category.vue";
  export default {
    name:'App',
    components: {Category}
  }
</script>

<style>
  .container,.foot{
    display: flex;
    justify-content: space-around;
  }
  h4{
    text-align: center;
  }
</style>