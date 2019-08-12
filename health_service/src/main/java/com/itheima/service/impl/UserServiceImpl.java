package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户服务实现类
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询用户对象（角色+权限数据）
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        //先根据用户名查询用户对象
        User user = userDao.findUserByUsername(username);
        //根据用户id查询角色对象
        if (user == null) {
            return null;
        }
        Integer userId = user.getId();//用户id
        Set<Role> roleSet = roleDao.findRoleByUserId(userId);
        //再根据角色id查询权限对象
        if (roleSet != null && roleSet.size() > 0) {
            for (Role role : roleSet) {
                Integer roleId = role.getId();///角色id
                Set<Permission> permissionSet = permissionDao.findPermissionByRoleId(roleId);
                if (permissionSet != null && permissionSet.size() > 0) {
                    //将权限放入角色对象中
                    role.setPermissions(permissionSet);
                }
            }
            //将角色数据放入用户对象中
            user.setRoles(roleSet);
        }
        return user;
    }

    /**
     * 保存检查组
     *
     * @param roleIds
     * @param user
     */
    @Override
    public void add(User user, List<Integer> roleIds) {
        //1.保存检查组表
        userDao.add(user);
        //2.保存检查组后 checkGroup.getId()
        Integer userId = user.getId();//检查组id
        //3.再循环往中间表插入数据 检查组id 检查项id(抽取一个公共方法出来)
        this.setCheckGroupAndCheckItem(userId, roleIds);
    }

    @Override
    public Result findPage(String queryString, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        Page<User> userPage = userDao.selectByCondition(queryString);//返回结果的 插件定义Page<T>
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, new PageResult(userPage.getTotal(), userPage.getResult()));
    }

    /**
     * 根据检查组id查询检查组对象
     *
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    /**
     * 根据检查组id查询检查项ids
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return userDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void edit(Integer[] roleIds, User user) {
        Integer userId = user.getId();

        //A.根据id更新检查组对象
        userDao.edit(user);
        //B.先根据检查组id将中间表已经关联的检查项记录删除
        userDao.deleteAssociation(userId);
        //C.根据检查组id和检查项ids再重新关联
        this.setCheckGroupAndCheckItem(userId, roleIds);
    }

    /**
     * 查询所有检查组数据
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 检查组检查项中间表公共方法
     *
     * @param roleIds
     * @param userId
     */
    public void setCheckGroupAndCheckItem(Integer userId, Integer[] roleIds) {
        //检查组ids非空判断
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("userId", userId);
                map.put("roleId", roleId);
                userDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    /**
     * 检查组检查项中间表公共方法
     */
    public void setCheckGroupAndCheckItem(Integer userId, List<Integer> roleIds) {
        //检查组ids非空判断
        if (roleIds != null && roleIds.size() > 0) {
            for (Integer roldId : roleIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("userId", userId);
                map.put("roleId", roldId);
                userDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    //根据检查组id 删除关联表中的信息
    public void deleteByGroupId(Integer userId) {
        userDao.deleteByGroupId(userId);
    }

    //根据id删除检查组 同时需要删除关联表中检查组和检查项的信息
    @Override
    public void deleteGroupById(Integer id) {
        //先删除关联表中的信息
        deleteByGroupId(id);
        //删除检查组信息
        userDao.deleteGroupById(id);
    }
}
