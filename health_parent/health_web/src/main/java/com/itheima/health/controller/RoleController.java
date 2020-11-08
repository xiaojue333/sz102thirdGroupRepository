package com.itheima.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;


    @PostMapping("/add")
    public Result add(@RequestBody Role role, Integer[] permissionIds){
        // 调用服务添加
        roleService.add(role, permissionIds);
        return new Result(true, MessageConstant.ADD_ROLE_SUCCESS);
    }


    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        // 调用服务分页查询
        PageResult<Role> pageResult = roleService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,pageResult);
    }


    @GetMapping("/findById")
    public Result findById(int id){
        Role role = roleService.findById(id);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,role);
    }


    @GetMapping("/findPermissionIdsByRoleId")
    public Result findPermissionIdsByRoleId(int id){
        List<Integer> permissionIds = roleService.findPermissionIdsByRoleId(id);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,permissionIds);
    }


    @PostMapping("/update")
    public Result update(@RequestBody Role role, Integer[] permissionIds){
        // 调用服务更新
        roleService.update(role,permissionIds);
        return new Result(true, MessageConstant.EDIT_ROLE_SUCCESS);
    }


    @PostMapping("/deleteById")
    public Result deleteById(int id){
        // 调用服务删除
        roleService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_ROLE_SUCCESS);
    }


    @GetMapping("/findAll")
    public Result findAll(){
        List<Role> list = roleService.findAll();
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,list);
    }
}
