//人员管理相关的配置
import axios from "axios";
import {nanoid} from "nanoid";

export default {
    namespaced:true,
    actions:{
        addPersonWang(context,value){
            if(value.name.indexOf('王')===0){
                context.commit('ADD_PERSON',value)
            }else{
                alert('添加的人必须姓王')
            }
        },
        addPersonServer(context){
            axios.get('https://api.apiopen.top/api/sentences').then(
                response=>{
                    context.commit('ADD_PERSON',{id:nanoid(),name:response.data.result.name})
                    console.log(response.data)
                },
                error=>{
                    alert(error.message)
                }
            )
        }
    },
    mutations:{
        ADD_PERSON(state,value){
            state.personList.unshift(value)
        }
    },
    getters:{
        firstPersonName(state){
            return state.personList[0].name
        }
    },
    state:{
        personList:[
            {id:'001',name:'张三'}
        ]
    }
}