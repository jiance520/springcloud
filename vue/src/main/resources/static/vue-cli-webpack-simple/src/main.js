import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import News  from './components/News'
//import Home  from './components/Home'
//import routerConfig from './router/router.config'
Vue.prototype.axios=axios
Vue.use(ElementUI)
Vue.use(VueRouter)
//const router = new VueRouter(routerConfig)
const myrouter = new VueRouter({
  routes:[ //注意不是routers:routers！
    {
      path:'/home',
      component:{
        template: '<h3>我是主页</h3>'
      }
    },
    {
      path:'/news',
      component: News
    },
    {path:"*",redirect:'/home'} //以上不存在的路径，设置默认url，重定向。
  ],
  mode:'history', //访问路径:http://locahost:8080/home,默认hash模式，访问路径http://locahost:8080/#home
  linkActiveClass:'active' //更新活动链接的class类名
})
new Vue({
  el: '#app',
  render: h => h(App),
  router:myrouter
})
