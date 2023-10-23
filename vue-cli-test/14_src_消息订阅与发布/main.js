//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//关闭Vue的生产提示
Vue.config.productionTip = false

/*const demo=Vue.extend({})
const d=new demo
Vue.prototype.x=d*/

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
})