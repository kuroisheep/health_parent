package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Permission;
import com.itheima.service.CheckItemService;
import com.itheima.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限控制层管理
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;

    /**
     * 新增检查项
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Permission permission){
        //调用service服务保存数据
        try {
            System.out.println("接受到了请求==========================="+permission);
            permissionService.add(permission);
            return new Result(true, MessageConstant.ADD_PERMISSION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.ADD_PERMISSION_FAIL);
        }
    }

    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping(value = "/findPage",method = RequestMethod.POST)
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            Result result = permissionService.findPage(queryPageBean.getQueryString(),queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL,e.getMessage());
        }
    }


    /**
     * 检查项删除
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(Integer id){
        try {
            permissionService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_PERMISSION_SUCCESS);
        } catch (RuntimeException e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.DELETE_PERMISSION_FAIL,e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.DELETE_PERMISSION_FAIL,e.getMessage());
        }
    }


    /**
     * 根据检查项id查询检查项数据
     * @return
     */
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public Result findById(Integer id){
        try {
            Permission permission =  permissionService.findById(id);
            return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS,permission);
        }
        catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL,e.getMessage());
        }
    }


    /**
     * 编辑检查项数据
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Result edit(@RequestBody Permission permission){
        try {
            permissionService.edit(permission);
            return new Result(true, MessageConstant.EDIT_PERMISSION_SUCCESS);
        }
        catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.EDIT_PERMISSION_FAIL,e.getMessage());
        }
    }


    /**
     * 查询所有检查项数据
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Result findAll(){
        try {
            List<Permission> list = permissionService.findAll();
            return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS,list);
        }
        catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL,e.getMessage());
        }
    }
}
