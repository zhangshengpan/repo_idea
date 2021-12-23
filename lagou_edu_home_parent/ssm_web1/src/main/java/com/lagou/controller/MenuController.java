package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> menuList = menuService.findAllMenu();
        return new ResponseResult(true,200,"查询所有菜单列表成功",menuList);
    }


    //回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        //根据id值来判断当前是添加操作还是更新操作   ，id为不为-1
        if(id == -1){
            //添加操作   回显信息中不需要查询menu信息
            List<Menu> menuList = menuService.findSubMenuByPid(-1);

            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);

            return new ResponseResult(true,200,"添加回显操作成功",map);
        }else{
            //是修改操作  回显所有信息
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuByPid(-1);
            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);

            return new ResponseResult(true,200,"回显操作成功",map);
        }
    }



}
