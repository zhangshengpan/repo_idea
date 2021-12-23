package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    //查询所有父子菜单信息
    public List<Menu> findSubMenuByPid(int pid);


    //查询所有菜单列表信息
    List<Menu> findAllMenu();

    //根据id查询menu
    Menu findMenuById(Integer id);

}
