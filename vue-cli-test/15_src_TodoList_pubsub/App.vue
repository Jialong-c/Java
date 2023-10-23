<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
        <MyHeader @addTodo="addTodo"/>
        <List :todos="todos"/>
        <MyFooter :todos="todos" @checkAllTodo="checkAllTodo" @clearAllTodo="clearAllTodo"/>
      </div>
    </div>
  </div>
</template>

<script>
  import pubsub from 'pubsub-js'
	import MyHeader from './components/MyHeader.vue'
	import MyFooter from './components/MyFooter.vue'
	import List from './components/List.vue'
	export default {
		name:'App',
    components:{
      MyHeader,
      MyFooter,
      List
    },
    data(){
      return{
        // todos:sessionStorage.getItem('todos')===null?[]:JSON.parse(sessionStorage.getItem('todos'))
        todos:JSON.parse(sessionStorage.getItem('todos')) || []
      }
        /*[
          {id:'001',title:'抽烟',done:true},
          {id:'002',title:'喝酒',done:false},
          {id:'003',title:'开车',done:true}
        ]*/
    },
    methods:{
      //添加todo
      addTodo(todoObj){
        //console.log('App组件收到数据：',x)
        this.todos.unshift(todoObj)
      },
      //勾选或取消勾选todo
      checkTodo(id){
        this.todos.forEach(
            todo=>{
              if(todo.id===id) todo.done=!todo.done
            }
        )
      },
      //删除todo
      //msgName用不到，使用_占位
      deleteTodo(_,id){
        this.todos=this.todos.filter(
            todo=>{
              return todo.id!==id
            }
        )
      },
      //全选或全不选
      checkAllTodo(done){
        this.todos.forEach(
            todo=>{
              todo.done=done
            }
        )
      },
      //清除全部已经完成的todo
      clearAllTodo(){
        this.todos=this.todos.filter(
            todo=>{
              return !todo.done
            }
        )
      }
    },
    watch:{
      todos:{
        //开启深度监视，监视done属性发生变化
        deep:true,
        handler(value){
          sessionStorage.setItem('todos',JSON.stringify(value))
        }
      }
    },
    mounted() {
      this.$bus.$on('checkTodo',this.checkTodo)
      //this.$bus.$on('deleteTodo',this.deleteTodo)
      this.pubId=pubsub.subscribe('deleteTodo',this.deleteTodo)
    },
    beforeDestroy() {
      this.$bus.$off('checkTodo')
      //this.$bus.$off('deleteTodo')
      pubsub.unsubscribe(this.pubId)
    }
  }
</script>

<style>
  /*base*/
  body {
    background: #fff;
  }

  .btn {
    display: inline-block;
    padding: 4px 12px;
    margin-bottom: 0;
    font-size: 14px;
    line-height: 20px;
    text-align: center;
    vertical-align: middle;
    cursor: pointer;
    box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
    border-radius: 4px;
  }

  .btn-danger {
    color: #fff;
    background-color: #da4f49;
    border: 1px solid #bd362f;
  }

  .btn-danger:hover {
    color: #fff;
    background-color: #bd362f;
  }

  .btn:focus {
    outline: none;
  }

  .todo-container {
    width: 600px;
    margin: 0 auto;
  }
  .todo-container .todo-wrap {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
  }
</style>