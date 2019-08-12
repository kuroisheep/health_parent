package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import com.itheima.service.MenuService;
import com.itheima.service.PermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限控制层管理
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    /**
     * 新增检查项
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Menu menu){
        //调用service服务保存数据
        try {

            menuService.add(menu);
            return new Result(true, MessageConstant.ADD_PERMISSION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.ADD_MENU_FAIL);
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
            Result result = menuService.findPage(queryPageBean.getQueryString(),queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.QUERY_MENU_FAIL,e.getMessage());
        }
    }


    /**
     * 检查项删除
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(Integer id){
        try {
            menuService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_MENU_SUCCESS);
        } catch (RuntimeException e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.DELETE_MENU_FAIL,e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL,e.getMessage());
        }
    }


    /**
     * 根据检查项id查询检查项数据
     * @return
     */
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public Result findById(Integer id){
        try {
            Menu menu =  menuService.findById(id);
            return new Result(true, MessageConstant.QUERY_MENU_SUCCESS,menu);
        }
        catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.QUERY_MENU_FAIL,e.getMessage());
        }
    }


    /**
     * 编辑检查项数据
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Result edit(@RequestBody Menu menu){
        try {
            menuService.edit(menu);
            return new Result(true, MessageConstant.EDIT_MENU_SUCCESS);
        }
        catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.EDIT_MENU_FAIL,e.getMessage());
        }
    }


    /**
     * 查询所有检查项数据
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Result findAll(){
        try {
            List<Menu> list = menuService.findAll();
            return new Result(true, MessageConstant.QUERY_MENU_SUCCESS,list);
        }
        catch (Exception e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.QUERY_MENU_FAIL,e.getMessage());
        }
    }
}
