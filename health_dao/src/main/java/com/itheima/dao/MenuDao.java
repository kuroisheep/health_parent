package com.itheima.dao;

import com.itheima.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashSet;
import java.util.List;

public interface MenuDao {

    LinkedHashSet<Menu> findMenusByRoleId(Integer roleId);

    List<Menu> findMenusByMenuId(@Param("roleId") int menuid, @Param("parentMenuId") int parentMenuId);
}
