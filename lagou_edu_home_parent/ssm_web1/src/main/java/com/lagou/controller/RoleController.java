package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //查询所有角色
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> roleList = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色成功", roleList);
        return responseResult;
    }

    @Autowired
    private MenuService menuService;

    //查询所有的父子菜单信息（分配菜单的第一个接口）
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        List<Menu> menuList = menuService.findSubMenuByPid(-1);//-1表示查询所有的父子级菜单

        //响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        return new ResponseResult(true,200,"查询所有父子菜单成功",map);
    }


    //根据角色ID查询该角色关联的菜单信息
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true,200,"查询角色关联的菜单信息成功",menuByRoleId);
    }


    //为角色分配菜单信息
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"为角色分配菜单信息成功",null);
    }

    //删除角色
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer roleId){

        roleService.deleteRole(roleId);
        return new ResponseResult(true,200,"删除角色成功",null);
    }

}
