package com.gec.system.controller;

import com.gec.model.system.SysUser;
import com.gec.model.vo.LoginVo;
import com.gec.system.exception.MyCustomerException;
import com.gec.system.service.SysUserService;
import com.gec.system.util.JwtHelper;
import com.gec.system.util.MD5Helper;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "登录管理控制器")
@RequestMapping(value = "/admin/system/index")
public class LoginController {

    @Autowired

    private SysUserService sysUserService;

    /**
     *  登录接口
     * @return
     */
// 登录接口
    @ApiOperation("登录方法")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo)
    {
        //1.根据username 查询数据
        SysUser sysUser =  this.sysUserService.getUserInfoUserName(loginVo.getUsername());

        //2.如果查询为空 给出提示
        if (sysUser==null){
            throw  new MyCustomerException(20001,"用户不存在..");
        }

        //3.比较密码  （使用用户输入的密码和数据库密码比较）
        String password = loginVo.getPassword();
        String passwordwithMD5 = MD5Helper.encrypt(password);

        if (!sysUser.getPassword().equals(passwordwithMD5)){
            throw new MyCustomerException(20001,"密码不正确");
        }


        //4.判断用户是否可用
        if (sysUser.getStatus().intValue()==0){
            throw new MyCustomerException(20001,"账号被停用..");
        }
        //5.根据用户userid和username去生成token，再通过map封装后返回
        String token = JwtHelper.createToken(sysUser.getId()+"", sysUser.getUsername());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token", token);

        return Result.ok(map);
    }
    /**
     *  获取用户信息
     */
    @ApiOperation("获取用户信息方法")
    @GetMapping("/info")
    public Result info(HttpServletRequest request)
    {
        //a.获取请求头的 token 字符串
        String token = request.getHeader("token");

        //b.从token 字符串获取用户名称 (id)
        String username = JwtHelper.getUsername(token);

        // c.根据用户名称获取用户信息 （a.基本信息  b.菜单权限信息  和 c.按钮权限信息）
        Map<String,Object> map =  sysUserService.getUserInfo(username);
        return Result.ok(map);
    }
    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }


}