<template>
  <div class="todo-footer" v-show="total">
    <label>
<!--      <input type="checkbox" :checked="isAll" @change="checkAll"/>-->
      <input type="checkbox" v-model="isAll"/>
    </label>
    <span>
          <span>已完成{{doneTotal}}</span> / 全部{{total}}
        </span>
    <button class="btn btn-danger" @click="clearAll">清除已完成任务</button>
  </div>
</template>

<script>
  import {task} from "@vue/cli-plugin-eslint/ui/taskDescriptor";

  export default {
    name:'MyFooter',
    props:['todos','checkAllTodo','clearAllTodo'],
    computed:{
      total(){
        return this.todos.length
      },
      doneTotal(){
        return this.todos.filter(
            todo=>todo.done
        ).length
      },
      isAll:{
        get(){
          return this.total===this.doneTotal&&this.total>0
        },
        set(value){
          this.checkAllTodo(value)
        }
      }
    },
    methods:{
      /*checkAll(event){
        //console.log(event.target.checked)
        this.checkAllTodo(event.target.checked)
      }*/
      clearAll(){
        if(confirm('确定清除已完成任务？')){
          this.clearAllTodo()
        }
      }
    }
  }
</script>

<style scoped>
  /*footer*/
  .todo-footer {
    height: 40px;
    line-height: 40px;
    padding-left: 6px;
    margin-top: 5px;
  }

  .todo-footer label {
    display: inline-block;
    margin-right: 20px;
    cursor: pointer;
  }

  .todo-footer label input {
    position: relative;
    top: -1px;
    vertical-align: middle;
    margin-right: 5px;
  }

  .todo-footer button {
    float: right;
    margin-top: 5px;
  }
</style>