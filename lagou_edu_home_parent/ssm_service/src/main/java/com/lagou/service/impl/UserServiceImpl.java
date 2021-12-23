package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    //用户登录
    @Override
    public User login(User user) throws Exception {
        //1.调用mapper方法     user2：包含了密文密码
        User user2 = (User) userMapper.login(user);

        //2.
        if(user2 != null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())){
            return user2;
        }else {
            return null;
        }
    }

    //分配角色（回显）
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVo userVo) {

        //1.根据用户ID清空中间表的关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        //2.再重新建立关联关系
        for (Integer roleId : userVo.getRoleIdList()) {
            //封装数据
            User_Role_relation relation = new User_Role_relation();
            relation.setUserId(userVo.getUserId());
            relation.setRoleId(roleId);

            Date date = new Date();
            relation.setCreatedTime(date);
            relation.setUpdatedTime(date);
            relation.setCreatedBy("system");
            relation.setUpdatedby("system");

            userMapper.userContextRole(relation);
        }
    }

    //获取用户权限信息
    @Override
    public ResponseResult getUserPermissions(Integer userId) {

        //1.获取用户所拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);

        //2.获取角色ID  保存到list集合中
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        //3.根据角色id查询父菜单
        List<Menu> parentMenus = userMapper.findParentMenuByRoleId(roleIds);

        //4.根据PID查询子菜单信息
        for (Menu parentMenu : parentMenus) {
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(parentMenu.getId());
            parentMenu.setSubMenuList(subMenuByPid);
        }

        //5.获取用户资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //6.封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenus);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }


}
