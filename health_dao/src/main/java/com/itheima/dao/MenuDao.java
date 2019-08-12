package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;

import java.util.List;
import java.util.Set;

/**
 * 权限接口
 */
public interface MenuDao {
//    /**
//     * 根据角色id查询权限列表
//     * @param roleId
//     * @return
//     */
//    Set<Permission> findPermissionByRoleId(Integer roleId);


    /**
     * 新增检查项
     * @param menu
     */
    void add(Menu menu);

    /**
     * 根据条件 查询分页数据
     * @param queryString
     * @return
     */
    Page<Menu> selectByCondition(String queryString);
    /**
     * 检查项删除
     * @return
     */
    void deleteById(Integer id);

    /**
     * 根据检查项id查询中间表数据
     * @param itemId
     * @return
     */
    int findCountByCheckItemId(Integer itemId);
    /**
     * 根据检查项id查询检查项数据
     * @return
     */
    Menu findById(Integer id);
    /**
     * 编辑检查项数据
     * @return
     */
    void edit(Menu menu);

    /**
     * 查询所有检查项
     * @return
     */
    List<Menu> findAll();
}
