package com.itheima.service;

import com.itheima.entity.Result;
import com.itheima.pojo.Role;

import java.util.List;

/**
 * 角色管理接口
 */
public interface OrleService {

    /**
     * 查询角色信息
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    Result findAll(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 查询所有权限
     * @return
     */
    Result findAllJurisDiction();

    void add(Role role, List<Integer> permissionIds);

    /**
     * 查询所有菜单
     * @return
     */
    Result findAllmenu();

}