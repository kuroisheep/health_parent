package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Role;
import com.itheima.service.OrleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/orle")
public class OrleController {

    @Reference
    private OrleService orleService;

    /**
     * 查询所有角色信息
     * @param queryPageBean
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public Result findAll(@RequestBody QueryPageBean queryPageBean){

        try {
            //调用service查询所有角色数据
            Result result = orleService.findAll(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
            //查询成功返回total和查询结果rows
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败
            return new Result(false,MessageConstant.GET_SETMEAL_INQUIRE_ORLE_FAILURE);
        }
    }

    /**
     * 查询所有权限
     *
     */

    @RequestMapping(value = "/inquireJurisdiction",method = RequestMethod.GET)
    public Result inquireJurisdiction(){
        try {
            //调用service查询所有的权限
            Result result = orleService.findAllJurisDiction();
            //查询所有权限成功
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //查询所有权限失败
            return new Result(false,MessageConstant.QUERY_ORLE_FAILURE);
        }
    }

    /**
     * 查询所有菜单
     */
    @RequestMapping(value = "/rolerequest",method = RequestMethod.GET)
    public Result rolerequest(){
        try {
            //调用service查询所有的权限
            Result result = orleService.findAllmenu();
            //查询所有权限成功
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //查询所有菜单失败
            return new Result(false,MessageConstant.QUERY_MENU_ORLE_FAILURE);
        }
    }


    /**
     * 新增角色信息
     */

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody  Role role,@RequestParam List<Integer> permissionIds) {
        //控制层一般都是接收请求 和响应数据
        try {
            orleService.add(role, permissionIds);
            return new Result(true, MessageConstant.QUERY_ORLE_NEWLY_INCREASED_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORLE_NEWLY_INCREASED_FAILURE);
        }
    }
}