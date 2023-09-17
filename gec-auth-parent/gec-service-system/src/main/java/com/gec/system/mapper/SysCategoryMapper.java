package com.gec.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysCategory;
import com.gec.model.system.SysRole;
import com.gec.model.vo.SysCategoryQueryVo;
import com.gec.model.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SysCategoryMapper extends BaseMapper<SysCategory> {
    //        分页方法
    public IPage<SysCategory> selectPage(IPage<SysCategory> page1, @Param("vo") SysCategoryQueryVo sysCategoryQueryVo);


}
