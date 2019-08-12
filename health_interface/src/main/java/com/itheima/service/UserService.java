package com.itheima.service;

import com.itheima.entity.Result;
import com.itheima.pojo.User;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 新增检查组
     * @param roleIds
     * @param user
     */
    void add(User user, List<Integer> roleIds);

    /**
     * 检查组分页
     * @param queryString
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result findPage(String queryString, Integer currentPage, Integer pageSize);

    /**
     * 根据检查组id查询检查组对象
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据检查组id查询检查项ids
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 编辑检查项
     * @param roleIds
     * @param user
     */
    void edit(Integer[] roleIds, User user);

    /**
     * 查询所有检查组数据
     * @return
     */
    List<User> findAll();

    /**
     * 删除
     * @param id
     */
    void deleteGroupById(Integer id);
}
