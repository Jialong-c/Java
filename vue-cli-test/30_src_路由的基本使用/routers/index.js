//创建并暴露路由器
import VueRouter from 'vue-router'
import About from "@/pages/About.vue";
import Home from "@/pages/Home.vue";

export default new VueRouter({
    routes:[
        {
            path:'/about',
            component:About
        },
        {
            path:'/home',
            component:Home
        }
    ]
})