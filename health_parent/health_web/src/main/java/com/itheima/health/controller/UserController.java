package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    /**
     * 获取登陆用户名
     * @return
     */
    @GetMapping("/getUsername")
    public Result getUsername(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("登陆的用户名:" + user.getUsername());
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
    }

    /**
     * 添加用户信息
     */
    @PostMapping("/add")
    public Result addUser(@RequestBody User user){
        userService.add(user);
        return new Result(true,"新增用户信息成功");
    }

    /**
     * 分页查询
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<User> pageResult = userService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, pageResult);
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    public Result delete(int id){
        userService.delete(id);
        return new Result(true,"删除用户信息成功");
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return new Result(true,"修改用户信息成功");
    }

    @GetMapping("/findById")
    public Result findById(int id){
        User user = userService.findById(id);
        return new Result(true,"回显数据成功",user);
    }
}
