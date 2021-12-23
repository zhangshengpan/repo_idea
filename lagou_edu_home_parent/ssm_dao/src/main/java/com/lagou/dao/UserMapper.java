package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    //用户分页&多条件组合查询
    List<User> findAllUserByPage(UserVo userVo);

    //用户登录（根据用户名查询具体的用户信息）
    User login(User user);


    //根据用户ID清空中间表
    void deleteUserContextRole(Integer userId);

    //分配角色
    void userContextRole(User_Role_relation relation);

    //1.根据用户ID查询关联的角色信息
    List<Role> findUserRelationRoleById(Integer id);

    //2.根据角色ID，查询角色所拥有的顶级菜单（-1）
    List<Menu> findParentMenuByRoleId(List<Integer> ids);

    //3.根据PID查询子菜单信息
    List<Menu> findSubMenuByPid(Integer pid);

    //4.获取用户所拥有的资源权限信息
    public List<Resource> findResourceByRoleId(List<Integer> ids);
}
