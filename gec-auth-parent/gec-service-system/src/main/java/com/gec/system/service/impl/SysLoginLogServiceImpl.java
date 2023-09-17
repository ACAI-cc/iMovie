package com.gec.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.model.system.SysLoginLog;
import com.gec.model.vo.SysLoginLogQueryVo;
import com.gec.system.mapper.SysLoginLogMapper;
import com.gec.system.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper,SysLoginLog> implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {

        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setStatus(status);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);

        this.sysLoginLogMapper.insert(sysLoginLog);

    }

    @Override
    public IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo) {
        //创建page对象
        Page<SysLoginLog> pageParam = new Page(page,limit);
        //获取条件值
        String username = sysLoginLogQueryVo.getUsername();
        String createTimeBegin = sysLoginLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysLoginLogQueryVo.getCreateTimeEnd();
        //封装条件
        QueryWrapper<SysLoginLog> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(username)) {
            wrapper.like("username",username);
        }
        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time",createTimeBegin);
        }
        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.le("create_time",createTimeEnd);
        }
        //调用mapper方法
        IPage<SysLoginLog> pageModel = sysLoginLogMapper.selectPage(pageParam, wrapper);
        return pageModel;
    }
//
//    @Override
//    public SysLoginLog getById(Long id) {
//        return this.sysLoginLogMapper.selectById(id);
//    }
}
