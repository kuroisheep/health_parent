package com.itheima.service;

import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Permission;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 业务处理层 服务接口
 */
public interface PermissionService {
    /**
     * 新增检查项
     * @param permission
     */
    void add(Permission permission);

    /**
     * 分页查询
     * @param queryString
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result findPage(String queryString, Integer currentPage, Integer pageSize);
    /**
     * 检查项删除
     * @return
     */
    void deleteById(Integer id);
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
