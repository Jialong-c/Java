<template>
  <div class="category">
    <h3>{{title}}分类</h3>
    <!--插槽,等着组件的使用者进行填充-->
    <slot :games="games">默认值</slot>
  </div>
</template>

<script>
  export default {
    name:'Category',
    props:['title'],
    data(){
      return {
        games:['红色警戒','穿越火线','劲舞团','超级玛丽'],
      }
    }
  }
</script>

<style scoped>
  .category{
    background-color: skyblue;
    width: 200px;
    height: 300px;
  }
  h3{
    text-align: center;
    background-color: orange;
  }
</style>