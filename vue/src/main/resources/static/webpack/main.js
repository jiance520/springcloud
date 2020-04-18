/*main.js使用ES6语法引入模块：js模板,对于文件，必须加./ */
import Vue from 'vue'
import App from './App.vue'
new Vue({
    el:'#app',
    /*components:{
        'my-app':App
    }*/
    render:function (h) {//使用render函数来渲染组件
        return h(App);
    }
    /*render: h => h(App)*/
});