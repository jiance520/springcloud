<template>
    <div>
        <h2>用户列表</h2>
        <ul>
            <li v-for="value in users">{{value}}</li>
        </ul>
    </div>
</template>
<script>
    export default {
        data(){
            return {
                users:['tom','jack','mike','alice']
            }
        }
    }
</script>