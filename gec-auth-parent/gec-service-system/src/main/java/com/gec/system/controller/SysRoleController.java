package com.gec.system.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.model.system.SysRole;

import com.gec.model.vo.AssginRoleVo;
import com.gec.model.vo.SysRoleQueryVo;

import com.gec.system.service.SysRoleService;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(tags = "角色管理控制器")
//restcontroller--->自带了responsebody
@RestController
//一级映射地址
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;



//    // 查询全部记录
//    @ApiOperation("查询全部接口")
//    @GetMapping("/findAll")
//    public List<SysRole> findAll()
//    {
//        List<SysRole> list = this.sysRoleService.list();
//        return list;
//    }
//
//    // 根据id 去逻辑删除
//    @ApiOperation("逻辑删除接口")
//    @DeleteMapping("/remove/{id}")
//    public boolean removeRole(@PathVariable Long id)
//    {
//        boolean isSuccess = this.sysRoleService.removeById(id);
//        return isSuccess;
//    }
//

    @ApiOperation("查询全部接口")
    @GetMapping("/findAll")
    public Result findAll()
    {
//        try {
//            int i = 1/0;
//        } catch (Exception e) {
//            // 手动抛出异常
//            throw new MyCustomerException(2001,"自定义异常..");
//        }
        List<SysRole> list = this.sysRoleService.list();
        return Result.ok(list);
    }

    // 根据id单个删除 去逻辑删除
    @ApiOperation("根据id删除角色")
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @DeleteMapping("/remove/{id}")
    public Result removeRole(@PathVariable String id)
    {
        boolean isSuccess = this.sysRoleService.removeById(id);
        if (isSuccess)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }

    // 分页 + 查询
    @ApiOperation("角色分页查询")
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @GetMapping("/{page}/{limit}")
    public Result findRoleByPageQuery(@PathVariable Long page,
                                      @PathVariable Long limit,
                                      SysRoleQueryVo roleQueryVo)
    {
//        封装分页条件
        IPage<SysRole> page1 = new Page<>(page,limit);
//        返回分页后的数据
        page1 =   this.sysRoleService.selectPage(page1,roleQueryVo);
        return Result.ok(page1);
    }



    // 添加
    @ApiOperation("添加角色")
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")

    @PostMapping("addRole")
    public Result addRole(@RequestBody SysRole sysRole)
    {
        boolean isSuccess = this.sysRoleService.save(sysRole);
        if (isSuccess)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }

    // 根据id 去获取一个role
    @GetMapping("findRoleById/{id}")
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")

    @ApiOperation("根据id查询")
    public Result findRoleById(@PathVariable String id)
    {
        SysRole sysRole = this.sysRoleService.getById(id);
        return Result.ok(sysRole);
    }


    // 修改
    @ApiOperation("修改角色")
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @PostMapping("updateRole")
    public Result updateRole(@RequestBody SysRole sysRole)
    {
        boolean isSuccess = this.sysRoleService.updateById(sysRole);
        if (isSuccess)
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
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")

    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> ids)
    {
        boolean isSuccess = this.sysRoleService.removeByIds(ids);
        if (isSuccess)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
}