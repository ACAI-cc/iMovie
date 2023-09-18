package com.gec.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.model.system.SysUser;
import com.gec.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ACAI
 * @since 2023-06-18
 */
public interface SysUserService extends IService<SysUser> {
//    分页
    IPage<SysUser> selectPage(IPage<SysUser> iPage, SysUserQueryVo sysUserQueryVo);

//    更新用户状态
    void updateStatus(Long id, Integer status);


    SysUser getUserInfoUserName(String username);
    Map<String, Object> getUserInfo(String username);
}
