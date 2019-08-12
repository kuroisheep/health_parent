package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;

import java.util.List;

/**
 * 角色模块
 */
public interface OrleDao {

    /**
     * 查询角色数据
     * @param queryString
     * @return
     */
    Page<Role> findAll(String queryString);

    /**
     * 查询所有权限
     * @return
     */
    List<Permission> findAllJurisDiction();

    /**
     * 查询所有菜单信息
     * @return
     */
    List<Menu> findAllmenu();

}
