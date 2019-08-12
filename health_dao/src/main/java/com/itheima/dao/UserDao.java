package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * 用户接口
 */
public interface UserDao {
    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 新增检查组
     * @param user
     */
    void add(User user);

    /**
     * 通过检查组往中间表插入数据
     * @param map
     */
    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    /**
     * 检查组分页
     * @param queryString
     * @return
     */
    Page<User> selectByCondition(String queryString);
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
     * 根据id更新检查组对象
     * @param user
     */
    void edit(User user);

    /**
     * 先根据检查组id将中间表已经关联的检查项记录删除
     * @param userId
     */
    void deleteAssociation(Integer userId);
    /**
     * 查询所有检查组数据
     * @return
     */
    List<User> findAll();

    void deleteByGroupId(Integer userId);

    void deleteGroupById(Integer username);
}
