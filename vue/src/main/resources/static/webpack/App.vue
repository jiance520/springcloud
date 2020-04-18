<template>
    <div id="itany">
        <h2>welcome to itany</h2>
        <button @click="change">{{name}}</button>
        <!--<user-list></user-list>-->
        <User></User>
    </div>
</template>


<script>
    import User from './components/User.vue'
    //ES6语法export default导出
    export default {
        data(){
            return {
                name:'tom'
            }
        },
        methods:{
            change(){
                this.name='汤母'
            }
        },
        components:{
            // 'user-list':User
            // User:User
            User
        }
    }
    console.log(111);
    //导入模块
</script>


<style scoped>
    body{
        background-color: #5bc0de;
    }
</style>