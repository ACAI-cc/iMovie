import request from '@/utils/request'
// 定义基本的url
const BASE_URL = '/admin/system/sysRole';

export default {
    // 列表 
    getPageList(page,limit,searchObj){
      return request({
          //  url: '/admin/system/role/'+page+"/"+limit,
          url: `${BASE_URL}/${page}/${limit}`,
          method: 'get',
          params: searchObj 
      })
    },

     // 删除
     removeId(id){
        return request({
            //  url: '/admin/system/role/'+page+"/"+limit,
            url: `${BASE_URL}/remove/${id}`,
            method: 'delete', // 提交方式
        })
      },
    saveRole(role){
        return request({
            url: `${BASE_URL}/addRole`,
            method: 'post', // 提交方式为 post
            data: role // 传递json 数据
        })
      },

       // 根据id 去获取一个角色
    getRoleById(id)
    {
      return request({
        url: `${BASE_URL}/findRoleById/${id}`,
        method: 'get', // 提交方式
      })  
    },

   updateRole(role)
    {
       return request({
        url: `${BASE_URL}/updateRole`,
        method: 'post', // 提交方式为 post
        data: role 
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


  //根据用户id查询用户已分配的角色
  getRolesByUserId(userId) {
    return request({
      url: `${BASE_URL}/toAssign/${userId}`,
      method: 'get'
    })
  },
  
  //分配角色
  assignRoles(assginRoleVo) {
    return request({
      url: `${BASE_URL}/doAssign`,
      method: 'post',
      data: assginRoleVo
    })
  }
}