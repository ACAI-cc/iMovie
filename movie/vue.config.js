const { defineConfig } = require('@vue/cli-service')
module.exports = {
  transpileDependencies: true,
  lintOnSave: false,

  devServer: {
    proxy: {
      '/api': { // 匹配所有以 '/dev-api'开头的请求路径
        target: 'http://localhost:8085',
        changeOrigin: true, // 支持跨域
        pathRewrite:{
          '^/api':''
        }
      }
    }
  },
}
