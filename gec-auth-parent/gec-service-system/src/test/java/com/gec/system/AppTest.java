package com.gec.system;

import com.gec.model.system.SysRole;
import com.gec.system.mapper.SysRoleMapper;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest{

    @Autowired
    private SysRoleMapper sysRoleMapper;

    // 查询全局数据
    @Test
    public void test1()
    {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        sysRoles.forEach(System.out::println);
    }


    @Test
    public void insert(){
        SysRole role = new SysRole();
        role.setRoleCode("204");
        role.setRoleName("测试4");
        role.setDescription("测试测试");

        int iret = sysRoleMapper.insert(role);

        System.out.println("iret = "+iret);
    }

    @Test
    public void update(){
        SysRole role = sysRoleMapper.selectById(17);
        role.setRoleName("多多大老板");

        int iret = sysRoleMapper.updateById(role);
        System.out.println("iret = "+iret);

    }

    /**
     * application-dev.yml 加入配置
     * 此为默认值，如果你的默认值和默认的一样，则不需要该配置
     * mybatis-plus:
     *   global-config:
     *     db-config:
     *       logic-delete-value: 1
     *       logic-not-delete-value: 0
     */
    @Test
    //通过id删除
    public void delete(){
        int iret = sysRoleMapper.deleteById(17);
        System.out.println("iret = "+iret);
    }

    @Test
//    批量删除
    public void batchDelete(){
        List<Integer> list = Arrays.asList(14,15,16);
        int iret = sysRoleMapper.deleteBatchIds(list);
        System.out.println("iret = "+iret);
}



}