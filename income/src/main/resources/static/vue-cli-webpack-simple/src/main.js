import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import routerConfig from './router.config';
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(VueRouter);
Vue.use(ElementUI);
const router = new VueRouter(routerConfig);
Vue.prototype.axios=axios;
new Vue({
  el: '#app',
  render: h => h(App),
  // components:{
  //   App
  // },
  // template:'<App/>',
  router
});
