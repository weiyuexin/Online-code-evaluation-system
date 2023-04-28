const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave: false,/*关闭语法检查*/
    // 开启代理服务器
    devServer: {
        port:8081,
        proxy: {
            '/api': {
                // target: 'http://8.130.99.217:8080',
                target: 'http://localhost:8080',
                pathRewrite: {'^/api': ''},
                changeOrigin: true
            }
        }
    }
})
