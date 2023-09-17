package com.gec.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.model.system.SysLoginLog;
import com.gec.model.system.SysOperLog;
import com.gec.model.vo.SysLoginLogQueryVo;
import com.gec.model.vo.SysOperLogQueryVo;
import com.gec.system.mapper.SysLoginLogMapper;
import com.gec.system.mapper.SysOperLogMapper;
import com.gec.system.service.SysLoginLogService;
import com.gec.system.service.SysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
@Transactional
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

//    @Override
//    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
//
//        SysLoginLog sysLoginLog = new
//                SysLoginLog();
//        sysLoginLog.setUsername(username);
//        sysLoginLog.setStatus(status);
//        sysLoginLog.setIpaddr(ipaddr);
//        sysLoginLog.setMsg(message);
//
//        this.sysLoginLogMapper.insert(sysLoginLog);
//
//    }

    @Override
    public IPage<SysOperLog> selectPage(long page, long limit, SysOperLogQueryVo sysOperLogQueryVo) {
        //创建page对象
        Page<SysOperLog> pageParam = new Page(page,limit);
        //获取条件值
        String oper_name = sysOperLogQueryVo.getOperName();
        String createTimeBegin = sysOperLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysOperLogQueryVo.getCreateTimeEnd();
        //封装条件
        QueryWrapper<SysOperLog> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(oper_name)) {
            wrapper.like("oper_name",oper_name);
        }
        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time",createTimeBegin);
        }
        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.le("create_time",createTimeEnd);
        }
        //调用mapper方法
        IPage<SysOperLog> pageModel = sysOperLogMapper.selectPage(pageParam, wrapper);
        return pageModel;
    }

//    @Override
//    public SysOperLog getById(Long id) {
//        return this.sysOperLogMapper.selectById(id);
//    }

    @Override
    public void removeById(Long id) {
        baseMapper.deleteById(id);
    }
}
