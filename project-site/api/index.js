import request from '~/utils/request'
export default {

  getTopBannerAdList() {
    return request({
      baseURL: 'http://localhost:8085',
      url: '/api/cms/ad/list/1517856550910205954',
      method: 'get'
    })
  },

  getIndexData() {
    return request({
      url: '/api/edu/index',
      method: 'get'
    })
  }
}
