package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Permission;

import java.util.List;
import java.util.Set;

/**
 * 权限接口
 */
public interface PermissionDao {
    /**
     * 根据角色id查询权限列表
     * @param roleId
     * @return
     */
    Set<Permission> findPermissionByRoleId(Integer roleId);


    /**
     * 新增检查项
     * @param permission
     */
    void add(Permission permission);

    /**
     * 根据条件 查询分页数据
     * @param queryString
     * @return
     */
    Page<Permission> selectByCondition(String queryString);
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
    Permission findById(Integer id);
    /**
     * 编辑检查项数据
     * @return
     */
    void edit(Permission permission);

    /**
     * 查询所有检查项
     * @return
     */
    List<Permission> findAll();
}
