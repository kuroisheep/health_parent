package com.itheima.service;

import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;

import java.util.List;

/**
 * 业务处理层 服务接口
 */
public interface MenuService {
    /**
     * 新增检查项
     * @param menu
     */
    void add(Menu menu);

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
