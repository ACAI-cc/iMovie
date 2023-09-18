package com.gec.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysLoginLog;
import com.gec.model.system.SysOperLog;
import com.gec.model.vo.SysLoginLogQueryVo;
import com.gec.model.vo.SysOperLogQueryVo;
import com.gec.system.service.SysLoginLogService;
import com.gec.system.service.SysOperLogService;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author ACAI
 * @since 2023-06-26
 */
@RestController
@RequestMapping("/admin/system/sys-oper-log")
@Api(tags = "操作日志控制器")
public class SysOperLogController {
    @Autowired
    private SysOperLogService sysOperLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result QueryOperLog(
            @PathVariable Long page,
            @PathVariable Long limit,
            SysOperLogQueryVo sysOperLogQueryVo) {

        IPage<SysOperLog> iPage = sysOperLogService.selectPage(page, limit, sysOperLogQueryVo);
        return Result.ok(iPage);
    }



    @ApiOperation("根据id删除操作日志")
    // 测试删除
    @DeleteMapping("/remove/{id}")
    public Result removeRole(@PathVariable Long id)
    {
        this.sysOperLogService.removeById(id);

        return Result.ok();

    }
}

