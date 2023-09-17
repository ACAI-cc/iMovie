import Vue from 'vue'
import VueRouter from 'vue-router'



Vue.use(VueRouter)

const routes = [

  {
    path:'/homePage',
    name:'homePage',
    component:()=>import('../views/homePage.vue'),
   
  },
  {
    path:'/moviePlayer',
    name:'moviePlayer',
    component:()=> import('../views/moviePlayer.vue')
  },
  
  // 重定向，设置默认页面
  {
    path: '*',
    redirect: '/homePage'
}
//  路由重定向，设置进入页面默认为home
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
