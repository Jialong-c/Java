//引入不再是Vue构造函数了，引入的是一个名为createApp的工厂函数
import { createApp } from 'vue'
import App from './App.vue'

//创建应用实例对象——app（类似于vue2中vm，但app比vm更轻）
const app=createApp(App)
app.mount('#app')

/*const vm=new Vue({
    render:h=>h(App)
})
vm.$mount('#app')*/
