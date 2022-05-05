import request from '@/utils/request'
export default {

  register(member) {
    return request({
      baseURL: 'http://localhost:8086',
      url: '/api/ucenter/member/register',
      method: 'post',
      data: member
    })
  }
}
