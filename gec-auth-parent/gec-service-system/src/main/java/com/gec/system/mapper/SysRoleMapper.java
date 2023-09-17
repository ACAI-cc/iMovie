package com.gec.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysRole;
import com.gec.model.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

//        分页方法
        public IPage<SysRole> selectPage(IPage<SysRole> page1, @Param("vo") SysRoleQueryVo roleQueryVo);


}