'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://127.0.0.1:8082"',  //8082是后端端口    //前端后端连接的桥梁
  OSS_PATH: '"https://project-gd-file.oss-cn-beijing.aliyuncs.com"'

})
// 开发环境特殊常量