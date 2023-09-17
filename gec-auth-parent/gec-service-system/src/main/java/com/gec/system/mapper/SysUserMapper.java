package com.gec.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysUser;
import com.gec.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author dafei
 * @since 2023-06-18
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    public IPage<SysUser> selectPage(IPage<SysUser> iPage, @Param("vo") SysUserQueryVo sysUserQueryVo);
}
