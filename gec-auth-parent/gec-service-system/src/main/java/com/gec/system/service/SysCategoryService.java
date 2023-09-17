package com.gec.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.model.system.SysCategory;
import com.gec.model.system.SysRole;
import com.gec.model.vo.SysCategoryQueryVo;
import com.gec.model.vo.SysRoleQueryVo;


public interface SysCategoryService extends IService<SysCategory> {
    //    分页器
    //    分页查询抽象方法
    IPage<SysCategory> selectPage(IPage<SysCategory> page1, SysCategoryQueryVo sysCategoryQueryVo);

}
