import request from './axios'

//定义基本路径
const BASE_URL = 'admin/system';

export default {

  // 列表查询
  getMoviePageInfo(page, limit, searchObj) {
    return request({
      //  url: '/admin/system/role/'+page+"/"+limit,
      url: `${BASE_URL}/sysMovie/selectPage?page=${page}&limit=${limit}`,
      method: 'get',
      params: searchObj
    })
  },

  // 查询分类
  findAll() {
    return request({
      url: `${BASE_URL}/sysCategory/findAll`,
      method: 'get'
    })
  },

  // 根据id获取palyAuth播放凭证
  getPlayAuthById(id) {
    return request({
      url: `${BASE_URL}/sysMovie/getPlayAuth?id=${id}`,
      method: 'get'
    })
  }
}