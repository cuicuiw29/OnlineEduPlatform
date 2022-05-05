import request from '~/utils/request'
import cookie from 'js-cookie'

export default {
  submitLogin(user) {
    return request({
      baseURL: 'http://localhost:8086',
      url: '/api/ucenter/member/login',
      method: 'post',
      data: user
    })
  },

  getLoginInfo() {
    return request({
      baseURL: 'http://localhost:8086',
      url: '/api/ucenter/member/get-login-info',
      method: 'get',
      // 通过请求头发送token
      header: { 'token': cookie.get('token') }
    })
  }
}
