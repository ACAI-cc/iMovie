package com.gec.system.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.gec.model.system.SysLoginLog;

import com.gec.model.vo.SysLoginLogQueryVo;

import com.gec.system.service.SysLoginLogService;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("登录日志控制器")
@RestController
@RequestMapping("/admin/system/sysLoginLog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result QueryLoginLog(
            @PathVariable Long page,
            @PathVariable Long limit,
                    SysLoginLogQueryVo sysLoginLogQueryVo) {

        IPage<SysLoginLog> iPage = sysLoginLogService.selectPage(page, limit, sysLoginLogQueryVo);
        return Result.ok(iPage);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysLoginLog sysLoginLog = sysLoginLogService.getById(id);
        return Result.ok(sysLoginLog);
    }

    // 根据id 去删除一个用户
    @ApiOperation("根据id 去删除一个用户")
    @DeleteMapping("/removeLogById/{id}")
    public Result removeUserById(@PathVariable Long id){
        boolean b = this.sysLoginLogService.removeById(id);
        if (b)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }
}