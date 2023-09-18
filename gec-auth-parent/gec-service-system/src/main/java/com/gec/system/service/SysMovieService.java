package com.gec.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.model.system.SysMovie;
import com.gec.model.system.SysRole;
import com.gec.model.vo.SysMovieQueryVo;
import com.gec.model.vo.SysRoleQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ACAI
 * @since 2023-06-20
 */

public interface SysMovieService extends IService<SysMovie> {
    //    分页查询抽象方法
    IPage<SysMovie> selectPage(IPage<SysMovie> page1, SysMovieQueryVo sysMovieQueryVo);
}
