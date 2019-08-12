package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Role;
import com.itheima.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 用户控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService service;

    /**
     * 获取用户名
     */
    @RequestMapping(value = "/getUsername",method = RequestMethod.POST)
    public Result getUsername(){
        //spring security框架上下文对象
        //getContext():权限容器 getAuthentication():获取认证对象     getPrincipal()获取用户信息
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
    }


    /**
     * 获取用户权限菜单
     * @param name
     * @return
     */
    @RequestMapping(value = "/getUserMenuByUsername",method = RequestMethod.POST)
    public Result getUserMenuByUsername(String name){
        try {
            com.itheima.pojo.User user = service.findUserByUsername(name);
            Set<Role> roleSet = user.getRoles();//对应角色集合
            LinkedHashSet<Menu> menus = null;
            if(roleSet != null && roleSet.size() > 0){
                for (Role role : roleSet) {
                   menus = role.getMenus();//获取菜单
                }
            }
            return new Result(true,MessageConstant.GET_MENU_SUCCESS,menus);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MENU_FAIL);
        }

    }

}
