//该文件用于创建Vuex中最为核心的store

//引入Vuex
import Vuex from 'vuex';
import Vue from "vue";

//使用vuex来集中管理状态,必要
//new store的前提是必须要使用Vuex插件
Vue.use(Vuex);

//求和相关的配置
import countOptions from './count'
//人员管理相关的配置
import personOptions from './person'

//创建并暴露store
export default new Vuex.Store({
    modules:{
        count:countOptions,
        person:personOptions
    }
})
