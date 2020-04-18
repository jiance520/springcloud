//Vue Loader v15+
const VueLoaderPlugin = require('vue-loader/lib/plugin');
module.exports={
    //告诉webpack服务器，入口文件是哪个
    entry:'./main.js',
    //配置入口文件输出位置，输出到项目根目录下，名字是buile.js
    output:{
        // publicPath:"./projectOutput/",//本地资源访问目录，也可以设成绝对路径
        path:__dirname,//两个下划线，项目根目录，
        // path: __dirname+"/initProject/",//生成文件存放地址，热启动资源访问目录，必须绝对路径，
        filename:'build.js'
    },
    plugins: [//插件
        new VueLoaderPlugin()
    ],
    // plugins: [uglifyPlugin, CommonsChunkPlugin, new ExtractTextPlugin('[name].css'),providePlugin],//插件集合
    //配置模块的加载器
    module:{
        rules:[//加载规则
            {
                test:/\.vue$/, //所有以.vue结尾的文件都由vue-loader加载
                loader:'vue-loader'
            },
            {
                test:/\.js$/, //所有以.js结尾的文件都由babel-loader加载,除了node_modules目录
                loader:'babel-loader',
                exclude:/node_modules/
            },
            {test:/\.css$/,use:['vue-style-loader','css-loader']}
            // {test:/\.less$/,use:['vue-style-loader','css-loader','less-loader']},
            // {test:/\.(jpg|png|bmp|gif|jpeg)$/,use:'url-loader'}
        ]
    }
};