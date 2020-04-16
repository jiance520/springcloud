<template>
  <div id="app">
    <img src="./assets/logo.png">
    <h1>{{ msg }}</h1>
    <h3>
      <router-link to="/home">主页</router-link>
      <router-link to="/news">新闻</router-link>
    </h3>
    <div>
      <keep-alive>
        <router-view></router-view>
      </keep-alive>
    </div>
    <button @click="send">发送axios</button>
    <Mybutton @click.native="send"></Mybutton>
    <!--按钮https://element.eleme.cn/#/zh-CN/component/quickstart-->
    <el-button type="primary">我的按钮</el-button>
    <el-button type="primary" icon="el-icon-edit">编辑</el-button>
    <el-button type="primary">上传<i class="el-icon-upload el-icon--right"></i></el-button>
  </div>
</template>

<script>
  //import axios  from 'axios';//在vue的原型上引入，实现全局使用
  import Mybutton from './components/Mybutton'
export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted() {
    console.log(this)
  },
  watch:{
    $route:function (newValue,oldValue) {
      console.log('路由发生变化，转到：'+newValue.path)
    }
  },
  methods:{
    send(){
      this.axios.get('https://api.github.com/users/tangyang8942')
        .then(resp=>{
        console.log(resp.data);
      }).catch(err=>{
        console.log(err);
      })
    }
  },
  components:{
    Mybutton
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
