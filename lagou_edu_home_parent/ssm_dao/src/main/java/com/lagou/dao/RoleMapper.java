package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    //查询所有角色&条件查询
    List<Role> findAllRole(Role role);


    //根据角色ID查询该角色关联的菜单信息
    List<Integer> findMenuByRoleId(Integer roleId);


    //根据roleID清空中间表的关联关系
    public void deleteContextMenu(Integer roleId);

    //为角色分配菜单信息
    void roleContextMenu(Role_menu_relation relation);


    //删除角色
    void deleteRole(Integer roleId);
}
