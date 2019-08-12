package com.itheima.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: TeFuir
 * @Date: 2019/8/12 0:28
 * @Description:
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Reference
    private UserService userService;


    /**
     * 新增检查组
     * @param roleIds  检查组对象
     * @param user  检查项ids
     * @return  返回成功或失败
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody User user, @RequestParam List<Integer> roleIds){
        //控制层一般都是接收请求 和响应数据
        try {


            userService.add(user,roleIds);
            return  new Result(true, MessageConstant.ADD_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false, MessageConstant.ADD_USER_FAIL);
        }
    }

    /**
     * 检查组分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping(value = "/findPage",method = RequestMethod.POST)
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            Result result = userService.findPage(queryPageBean.getQueryString(),queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.QUERY_USER_FAIL,e.getMessage());
        }
    }

    /**
     * 根据检查组id查询检查组对象
     */
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public Result findById(Integer id){
        try {
            com.itheima.pojo.User user = userService.findById(id);
            System.out.println(user+"===============================");
            return new Result(true, MessageConstant.QUERY_USER_SUCCESS,user);
        } catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.QUERY_USER_FAIL);
        }
    }

    /**
     * 根据检查组id查询检查项ids
     * 成功失败提示未加
     */
    @RequestMapping(value = "/findCheckItemIdsByCheckGroupId",method = RequestMethod.GET)
    public Result findCheckItemIdsByCheckGroupId(Integer id){
        try {
            List<Integer> list = userService.findCheckItemIdsByCheckGroupId(id);
            System.out.println(list+"+++++++++++++++++++++++++++++++++++++++++++++++++++");
            return new Result(true, MessageConstant.QUERY_CHECKITEM_IDS_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_IDS_FAIL);
        }
    }

    /**
     * 编辑检查组 edit
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Result edit(Integer[] roleIds,@RequestBody User user){
        try {
            userService.edit(roleIds,user);
            return new Result(true, MessageConstant.EDIT_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_USER_FAIL);
        }
    }

    /**
     * 查询所有检查组
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Result findAll(){
        try {
            List<com.itheima.pojo.User> userList = userService.findAll();
            return new Result(true, MessageConstant.QUERY_USER_SUCCESS,userList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_USER_FAIL);
        }
    }

    //根据id删除检查组
    @RequestMapping("/delete")
    public Result deleteGroupById(Integer id){
        try {
            userService.deleteGroupById(id);
            return new Result(true,MessageConstant.DELETE_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_USER_FAIL);
        }
    }
}
