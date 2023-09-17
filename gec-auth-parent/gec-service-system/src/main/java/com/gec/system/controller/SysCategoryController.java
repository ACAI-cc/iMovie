package com.gec.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.model.system.SysCategory;
import com.gec.model.vo.SysCategoryQueryVo;

import com.gec.system.service.SysCategoryService;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "分类管理控制器")
@RestController
@RequestMapping("admin/system/sysCategory")
public class SysCategoryController {

    @Autowired
    private SysCategoryService sysCategoryService;

//    分页查询
    @ApiOperation("角色分页查询")
    @GetMapping("/{page}/{limit}")
    public Result findCategoryByPageQuery(@PathVariable Long page, @PathVariable Long limit, SysCategoryQueryVo sysCategoryQueryVo){
//        封装分页条件
        IPage<SysCategory> page1 = new Page<>(page,limit);
//        返回分页后的数据
        page1 = this.sysCategoryService.selectPage(page1, sysCategoryQueryVo);

        return Result.ok(page1);
    }
    @ApiOperation("获取全部分类")
    @GetMapping("/findAll")
    public Result findAll(){
        List<SysCategory> list = this.sysCategoryService.list();
        return Result.ok(list);
    }


    // 根据id单个删除 去逻辑删除
    @ApiOperation("逻辑删除接口")
    @PreAuthorize("hasAuthority('btn.sysCategory.delete')")

    @DeleteMapping("/remove/{id}")
    public Result removeRole(@PathVariable String id)
    {
        boolean isSuccess = this.sysCategoryService.removeById(id);
        if (isSuccess)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }

    // 添加
    @ApiOperation("添加角色")

    @PreAuthorize("hasAuthority('btn.sysCategory.add')")
    @PostMapping("addCategory")
    public Result addRole(@RequestBody SysCategory sysCategory)
    {
        boolean isSuccess = this.sysCategoryService.save(sysCategory);
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
    @GetMapping("findCategoryById/{id}")
    @ApiOperation("根据id查询")
    public Result findRoleById(@PathVariable String id)
    {
        SysCategory sysCategory = this.sysCategoryService.getById(id);
        return Result.ok(sysCategory);
    }


    // 修改
    @ApiOperation("修改角色")
    @PostMapping("updateCategory")
    public Result updateRole(@RequestBody SysCategory sysCategory)
    {
        boolean isSuccess = this.sysCategoryService.updateById(sysCategory);
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

    @PreAuthorize("hasAuthority('btn.sysCategory.delete')")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> ids)
    {
        boolean isSuccess = this.sysCategoryService.removeByIds(ids);
        if (isSuccess)
        {
            return Result.ok();
        }
        else
        {
            return Result.fail();
        }
    }

}
