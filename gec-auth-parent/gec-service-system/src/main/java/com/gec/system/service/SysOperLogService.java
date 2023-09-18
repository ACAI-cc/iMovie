package com.gec.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysLoginLog;
import com.gec.model.system.SysOperLog;
import com.gec.model.vo.SysLoginLogQueryVo;
import com.gec.model.vo.SysOperLogQueryVo;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author ACAI
 * @since 2023-06-26
 */
public interface SysOperLogService{
    //条件分页查询登录日志
    IPage<SysOperLog> selectPage(long page, long limit, SysOperLogQueryVo sysOperLogQueryVo);

    void removeById(Long id);
}
