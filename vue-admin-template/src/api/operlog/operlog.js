import request from '@/utils/request'

// 定义公共的api 
const BASE_URL = '/admin/system/sys-oper-log';

export default{
    // 查询用户和分页
    getOperLogPageInfo(page,limit,searchObj){
        return request({
          url:`${BASE_URL}/${page}/${limit}`,
          method: 'get',
          params:searchObj
        })
      },

                     // 删除
    removeId(id){
        return request({
            
            url: `${BASE_URL}/remove/${id}`,
            method: 'delete', // 提交方式
        })
      },
}
