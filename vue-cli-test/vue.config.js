const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  pages:{
    index:{
      //入口
      entry:'src/main.js'
    }
  },
  //关闭语法检查
  lintOnSave:false,
  //开启代理服务器（方式一）
  /*devServer:{
      proxy: 'http://localhost:5000', //将请求转发给端口号5000，注意这里只能配置单个代理，不能配置多个代理
  }*/
  //开启代理服务器（方式一）
  devServer:{
    proxy:{
      //请求前缀
      '/api':{
        target:'http://localhost:5000',
        //正则表达式匹配请求前缀，并替换成空字符串
        pathRewrite:{'^/api':''},
        ws: true, //用于支持websocket
        changeOrigin: true, //用于控制请求头中host的值,false表示5000端口服务器询问代理服务器端口时代理服务器如实回答，否则回答5000端口
      },
      'demo':{
        target: 'http://localhost:5001',
        pathRewrite: {'^/demo':''},
        ws: true,
        changeOrigin: true
      }
    }
  }
})
