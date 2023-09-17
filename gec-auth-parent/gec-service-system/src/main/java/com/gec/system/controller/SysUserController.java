package com.gec.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.model.system.SysUser;
import com.gec.model.vo.SysUserQueryVo;
import com.gec.system.service.SysUserService;
import com.gec.system.util.MD5Helper;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author dafei
 * @since 2023-06-18
 */
@RestController
@Api(tags = "用户控制器")
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    // 分页和条件查询
    @ApiOperation("分页和条件查询")
    @GetMapping("/{page}/{limit}")
    public Result selectUserPageByVo(@PathVariable Long page,
                                     @PathVariable Long limit,
                                     SysUserQueryVo sysUserQueryVo)
    {

        System.out.println(sysUserQueryVo);

        IPage<SysUser> iPage = new Page<>(page,limit);

        iPage =   this.sysUserService.selectPage(iPage,sysUserQueryVo);

        return Result.ok(iPage);
    }

    // 添加用户
    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    public Result addUser(@RequestBody SysUser sysUser) {


        // 通过MD5 工具类
        String passwordWithMD5 = MD5Helper.encrypt(sysUser.getPassword());
        // 将加密密码设置给sysUser
        sysUser.setPassword(passwordWithMD5);

        boolean b = this.sysUserService.save(sysUser);
        if (b)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }
    // 根据id 去获取一个用户
    @ApiOperation("根据id 去获取一个用户")
    @GetMapping("/findUserById/{id}")
    public Result findUserById(@PathVariable Long id)
    {
        SysUser sysUser = this.sysUserService.getById(id);
        return Result.ok(sysUser);
    }

    // 修改
    @ApiOperation("修改实现")
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody SysUser sysUser)
    {
        
        boolean b = this.sysUserService.updateById(sysUser);
        if (b)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }

    // 根据id 去删除一个用户
    @ApiOperation("根据id 去删除一个用户")
    @DeleteMapping("/removeUserById/{id}")
    public Result removeUserById(@PathVariable Long id){
        boolean b = this.sysUserService.removeById(id);
        if (b)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }

    // 批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("/batchRoleByIds")
    public Result removeBatchUserByIds(@RequestBody List<Long> ids)
    {
        boolean b = this.sysUserService.removeByIds(ids);
        if (b)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }

    // 更改用户状态
    @ApiOperation("更改用户状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id,
                               @PathVariable Integer status)
    {
        this.sysUserService.updateStatus(id,status);
        return Result.ok();
    }

}
