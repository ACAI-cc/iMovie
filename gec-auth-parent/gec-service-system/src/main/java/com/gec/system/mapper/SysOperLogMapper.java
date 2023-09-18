package com.gec.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysOperLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.model.vo.SysOperLogQueryVo;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author ACAI
 * @since 2023-06-26
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    public IPage<SysOperLog> selectPage(IPage<SysOperLog> page1, @Param("vo") SysOperLogQueryVo operLogQueryVo);

}
