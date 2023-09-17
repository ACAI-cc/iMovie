import request from '@/utils/request'
// 定义基本url
const BASE_URL = '/admin/system/sysMovie'

export default {

    // 列表查询
    getMoviePageInfo(page, limit, searchObj) {
        return request({
            //  url: '/admin/system/role/'+page+"/"+limit,
            url: `${BASE_URL}/selectPage?page=${page}&limit=${limit}`,
            method: 'get',
            params: searchObj
        })
    },
    // 删除
    removeMovieById(id) {
        return request({
            //  url: '/admin/system/role/'+page+"/"+limit,
            url: `${BASE_URL}/removeMovie/${id}`,
            method: 'delete', // 提交方式
        })
    },
    addMovie(movie) {
        return request({
            url: `${BASE_URL}/addMovie`,
            method: 'post', // 提交方式为 post
            data: movie // 传递json 数据
        })
    },

    // 根据id 去获取一个角色
    getMovieById(id) {
        return request({
            url: `${BASE_URL}/findMovieById/${id}`,
            method: 'get', // 提交方式
        })
    },

    updateMovie(movie) {
        return request({
            url: `${BASE_URL}/updateMovie`,
            method: 'post', // 提交方式为 post
            data: movie
        })
    },

    //批量 删除
    bactchremoveId(ids) {
        return request({
            url: `${BASE_URL}/removeMovieByIds`,
            method: 'delete', // 提交方式
            data: ids
        })
    },
  // 根据id获取palyAuth播放凭证
  getPlayAuthById(id){
    return request({
      url:`${BASE_URL}/getPlayAuth?id=${id}`,
      method: 'get'
    })
  }


}