<template>
  <transition name="todo" appear>
    <li>
      <label>
        <input type="checkbox" :checked="todo.done" @change="handleCheck(todo.id)"/>
        <!--      也能实现功能，但不推荐-->
        <!--      <input type="checkbox" v-model="todo.done"/>-->
        <span v-show="!todo.isEdit">{{todo.title}}</span>
        <!--      @blur：失去焦点事件-->
        <input
            v-show="todo.isEdit"
            type="text"
            :value="todo.title"
            @blur="handleBlur(todo,$event)"
            ref="inputTitle"
        >
      </label>
      <button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
      <button v-show="!todo.isEdit" class="btn btn-edit" @click="handleEdit(todo)">编辑</button>
    </li>
  </transition>
</template>

<script>
  import pubsub from "pubsub-js";
  export default {
    name:'Item',
    //声明接收todo对象
    props:['todo'],
    methods:{
      //勾选或取消勾选
      handleCheck(id){
        //通知App组件将对应的todo对象的done值取反
        //this.checkTodo(id)
        this.$bus.$emit('checkTodo',id)
      },
      //删除
      handleDelete(id){
        if(confirm('确定删除？')){
          //console.log(id)
          //this.deleteTodo(id)
          //this.$bus.$emit('deleteTodo',id)'
          pubsub.publish('deleteTodo',id)
        }
      },
      //编辑
      handleEdit(todo){
        if(todo.hasOwnProperty('isEdit')){
          todo.isEdit=true
        }else{
          this.$set(todo,'isEdit',true)
        }
        //执行顺序，先执行isEdit和focus后再更新dom，直接使用$refs不能使隐藏的input框focus
        //dom更新结束后执行$nextTick
        this.$nextTick(function (){
          this.$refs.inputTitle.focus()
        })
      },
      handleBlur(todo,event){
        todo.isEdit=false
        //console.log('updateTodo',todo.id,event.target.value)
        if(!event.target.value.trim()) return alert('输入不能为空！')
        this.$bus.$emit('updateTodo',todo.id,event.target.value)
      }
    }
  }
</script>

<style scoped>
  /*item*/
  li {
    list-style: none;
    height: 36px;
    line-height: 36px;
    padding: 0 5px;
    border-bottom: 1px solid #ddd;
  }

  li label {
    float: left;
    cursor: pointer;
  }

  li label li input {
    vertical-align: middle;
    margin-right: 6px;
    position: relative;
    top: -1px;
  }

  li button {
    float: right;
    display: none;
    margin-top: 3px;
  }

  li:before {
    content: initial;
  }

  li:last-child {
    border-bottom: none;
  }

  li:hover{
    background-color: #dddddd;
  }

  li:hover button{
    display: block;
  }

  /*进入的起点，离开的终点*/
  .todo-enter{
    transform:translateX(-100%);
  }
  .todo-leave-to{
    transform: translateX(+100%);
  }
  /*进入的过程，离开的过程*/
  .todo-enter-active,
  .todo-leave-active{
    transition: linear .5s;
  }
  /*进入的终点,离开的起点*/
  .todo-enter-to,
  .todo-leave{
    transform: translateX(0);
  }
</style>