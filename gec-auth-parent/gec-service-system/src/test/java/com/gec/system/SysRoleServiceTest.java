//package com.gec.system;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.gec.model.system.SysRole;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.Arrays;
//import java.util.List;
//@SpringBootTest(classes = App.class)//指向启动类
//@RunWith(SpringJUnit4ClassRunner.class)
//
//public class SysRoleServiceTest {
//
////    调用业务逻辑层--->今后在控制层调用。
////    控制层调用业务逻辑层，业务逻辑层调用数据访问层
//    @Autowired
//    private SysRoleService sysRoleService;
//
//    @Test
//    public void testSelectList() {
//        System.out.println(("----- selectAll method test ------"));
//        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
//        //所以不填写就是无任何条件--->无条件查询，查询全部
//        List<SysRole> roles = sysRoleService.list();
//        for (SysRole role : roles) {
//            System.out.println("role = " + role);
//        }
//    }
//
//    @Test
//    public void testQueryWrapper() {
//        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
////        条件查询，使用ueryWrapper封装条件进行查询
//        queryWrapper.eq("role_code", "dafei");
//        List<SysRole> users = sysRoleService.list(queryWrapper);
//        System.out.println(users);
//    }
//
//    @Test
//    public void testInsert(){
//        SysRole sysRole = new SysRole();
//        sysRole.setRoleName("测试4");
//        sysRole.setRoleCode("role");
//        sysRole.setDescription("测试测试");
////        新增
//        boolean result = sysRoleService.save(sysRole);
//        System.out.println(result); //成功还是失败
//    }
//
////    @Test
////    public void testUpdateById(){
////        SysRole sysRole = new SysRole();
////
////        sysRole.setId(14l);
////        sysRole.setRoleName("duoduoduoduo");
////
////        boolean result = sysRoleService.updateById(sysRole);
////        System.out.println(result);
////
////    }
//
//    @Test
//    public void testDeleteById(){
//        boolean result = sysRoleService.removeById(2L);
//        System.out.println(result);
//    }
//    @Test
////    批量删除
//    public void batchDelete(){
//        List<Integer> list = Arrays.asList(14,15,16);
//        boolean iret = sysRoleService.removeByIds(list);
//        System.out.println("iret = "+iret);
//    }
//
//
//
//}