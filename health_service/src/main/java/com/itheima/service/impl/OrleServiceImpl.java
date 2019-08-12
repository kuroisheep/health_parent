package com.itheima.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.OrleDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.OrleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 角色管理 服务层
 */
@Transactional
@Service(interfaceClass = OrleService.class)
public class OrleServiceImpl implements OrleService {

    @Autowired
    private OrleDao orleDao;

    /**
     * 查询角色信息
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public Result findAll(Integer currentPage, Integer pageSize, String queryString) {
        //使用分页根据
        PageHelper.startPage(currentPage,pageSize);
        //调用dao，查询数据库
        Page<Role> rolePage = orleDao.findAll(queryString);
        //查询成功返回数据
        return new Result(true, MessageConstant.GET_SETMEAL_INQUIRE_ORLE_SUCCEED,new PageResult(rolePage.getTotal(),rolePage.getResult()));
    }

    /**
     * 查询所有权限
     * @return
     */
    @Override
    public Result findAllJurisDiction() {
        //调用dao进行权限查询
        List<Permission> permissionList = orleDao.findAllJurisDiction();
        if (permissionList != null){
            //查询成功
            return new Result(true,MessageConstant.QUERY_ORLE_SUCCESS,permissionList);
        }
        return new Result(false,MessageConstant.QUERY_ORLE_FAILURE);
    }


    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public Result findAllmenu() {
        //调用dao进行权限查询
        List<Menu> menuList = orleDao.findAllmenu();
        if (menuList != null){
            //查询成功
            return new Result(true,MessageConstant.QUERY_MENU_ORLE_SUCCESS,menuList);
        }
        return new Result(false,MessageConstant.QUERY_MENU_ORLE_FAILURE);
    }






    /**
     * 新增角色
     * @param role
     * @param permissionIds
     */
    @Override
    public void add(Role role, List<Integer> permissionIds) {
     /*  //1.保存角色表
        orleDao.add(role);
       // 2.保存角色表后，permissionIds
        Integer groupId = role.getId();//检查组id
        //3.再循环往中间表插入数据 检查组id 检查项id(抽取一个公共方法出来)
        this.setCheckGroupAndCheckItem(groupId,checkitemIds);*/

    }


}