import request from '@/utils/request'

//定义基本路径
const BASE_URL = 'admin/system/sysCategory';

export default {

  findAll() {
    return request({
      url: `${BASE_URL}/findAll`,
      method: 'get'
    })
  },
  //列表
  getPageList(page, limit, searchObj) {
    return request({
      url: `${BASE_URL}/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },
  // 删除
  removeId(id) {
    return request({
      //  url: '/admin/system/role/'+page+"/"+limit,
      url: `${BASE_URL}/remove/${id}`,
      method: 'delete', // 提交方式
    })
  },
  saveCategory(category) {
    return request({
      url: `${BASE_URL}/addCategory`,
      method: 'post', // 提交方式为 post
      data: category // 传递json 数据
    })
  },

  // 根据id 去获取一个角色
  getCategoryById(id) {
    return request({
      url: `${BASE_URL}/findCategoryById/${id}`,
      method: 'get', // 提交方式
    })
  },

  updateCategory(category) {
    return request({
      url: `${BASE_URL}/updateCategory`,
      method: 'post', // 提交方式为 post
      data: category
    })
  },
  //批量 删除
  bactchremoveId(ids) {
    return request({
      url: `${BASE_URL}/batchRemove`,
      method: 'delete', // 提交方式
      data: ids
    })
  },



}