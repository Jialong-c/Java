//创建并暴露路由器
import VueRouter from 'vue-router'
import About from "@/pages/About.vue";
import Home from "@/pages/Home.vue";
import News from "@/pages/News.vue";
import Message from "@/pages/Message.vue";
import Detail from "@/pages/Detail.vue";

const router= new VueRouter({
    routes:[
        {
            name:'guanyu',
            path:'/about',
            component:About,
            meta:{title:'关于'}
        },
        {
            name:'zhuye',
            path:'/home',
            component:Home,
            meta:{title:'主页'},
            children:[
                {
                    path:'news',
                    component:News,
                    meta:{
                        isAuth:true,
                        title:'新闻'
                    }
                },
                {
                    name:'xiaoxi',
                    path:'message',
                    component:Message,
                    meta:{
                        isAuth:true,
                        title:'消息'
                    },
                    beforeEnter(to,from,next){
                        console.log('独享路由守卫',to,from)
                        if(to.meta.isAuth){//判断是否需要鉴权
                            // if(to.path==='/home/news'||to.path==='/home/message'){
                            if(sessionStorage.getItem('school')==='XD'){
                                next()
                            }else{
                                alert('无权限')
                            }
                        }else{
                            next()
                        }
                    },
                    children:[
                        {
                            name:'xiangqing',
                            path:'detail',
                            component:Detail,
                            meta:{
                                isAuth:true,
                                title:'详情'
                            },
                            //props的第一种写法值为对象,该对象的所有key-value都会以props的形式传给detail组件(死数据)
                            // props:{
                            //     a:1,
                            //     b:'hello'
                            // }

                            //props的第二种写法，值为布尔值,若布尔值为真，就会把该路由组件收到的所有params(注意如果是query参数不会奏效的)参数以props的形式传递给detail组件
                            // props: true

                            //props的第三种写法,值为函数  $route.query.id
                           props($route){
                                return{
                                    id:$route.query.id,
                                    title:$route.query.title
                                }
                           }
                        }
                    ]
                }
            ]
        }
    ]
})

//全局后置路由守卫——初始化的时候被调用、每次路由切换之后被调用
router.afterEach((to, from)=>{
    console.log('后置路由守卫',to,from)
    document.title=to.meta.title||'vue-test'
})

export default router