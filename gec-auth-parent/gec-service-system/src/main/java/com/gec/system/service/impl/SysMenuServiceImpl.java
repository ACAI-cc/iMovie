
package com.gec.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gec.model.system.SysMenu;
import com.gec.model.system.SysRoleMenu;
import com.gec.model.vo.AssginMenuVo;
import com.gec.model.vo.RouterVo;
import com.gec.system.exception.MyCustomerException;
import com.gec.system.mapper.SysMenuMapper;
import com.gec.system.mapper.SysRoleMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.system.service.SysMenuService;
import com.gec.system.util.RouterHelper;
import com.gec.system.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author duo
 * @since 2023-06-19
 */

@Service
@Transactional
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        //1.获取所有的菜单
        List<SysMenu> menuList = this.baseMapper.selectList(null);
        //2.所有菜单数据转换要求数据格式
        List<SysMenu> bulidTree = MenuHelper.bulidTree(menuList);

        return bulidTree;
    }

    @Override
    public void removeMenuById(Long id) {
        //查询当前删除菜单下面是否子菜单
        //根据id = parentid
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
//        计算wrapper中包含的个数，看看子节点个数
        Integer c = baseMapper.selectCount(wrapper);
        Long count = Long.valueOf(c.toString());

        if (count > 0) {//有子菜单
            throw new MyCustomerException(201, "请先删除子菜单");
        }
        //调用删除
        baseMapper.deleteById(id);
    }


    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        //获取所有status为1的权限列表
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        List<SysMenu> menuList = baseMapper.selectList(queryWrapper);

        //根据角色id获取角色权限
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);

        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(wrapper);

        //获取该角色已分配的所有权限id
        List<Long> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }
        //遍历所有权限列表---->若权限表中有该角色已分配的权限则将选中
        for (SysMenu sysMenu : menuList) {
            if (roleMenuIds.contains(sysMenu.getId())) {
                //设置该权限已被分配
                sysMenu.setSelect(true);
            } else {
                sysMenu.setSelect(false);
            }
        }
        //将权限列表转换为权限树
        List<SysMenu> sysMenus = MenuHelper.bulidTree(menuList);
        return sysMenus;
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //删除已分配的权限
        sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", assginMenuVo.getRoleId()));
        //遍历所有已选择的权限id
        for (Long menuId : assginMenuVo.getMenuIdList()) {
            if (menuId != null) {
                //创建SysRoleMenu对象
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
                //添加新权限
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }

    @Override
    public List<RouterVo> findUserMenuList(Long userId) {
        //超级管理员admin账号id为：1
        // 我们约定 admin 是 超级管理员拥有所有的权限
        List<SysMenu> sysMenuList = null;

        if (userId.longValue() == 1) {
            //a.  表示是超级管理员
            sysMenuList = baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1).orderByAsc("sort_value"));
        } else {

            // b. 其他非超级管理员的 用户
            sysMenuList = baseMapper.findMenuListByUserId(userId);
        }



        //c.构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.bulidTree(sysMenuList);

        //TODO  MeunHelper返回的数据属性和 前端路由的属性不一致，所以要处理
        //d.构建路由
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenuTreeList);

        return routerVoList;
    }
    @Override
    public List<String> findUserPermsList(Long id) {
        //超级管理员admin账号id为：1
        List<SysMenu> sysMenuList = null;
        if (id.longValue() == 1) {
            sysMenuList = this.baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            sysMenuList = this.baseMapper.findMenuListByUserId(id);
        }
        //创建返回的集合
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if(sysMenu.getType() == 2){
                permissionList.add(sysMenu.getPerms());
            }
        }
        return permissionList;
    }
}




