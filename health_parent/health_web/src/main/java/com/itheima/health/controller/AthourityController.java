package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Permission;
import com.itheima.health.service.AthourityService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/athourity")
public class AthourityController {
    @Reference
    private AthourityService athourityService;




    @RequestMapping("/findAll")
    public Result findAll() {

        List<Permission> list= athourityService.findAll();
        if (list == null) {
            new Result(false, "获取数据出错,请联系管理员");
        }
        return new Result(true, "获取分页数据成功", list);
    }



    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {

        PageResult result = athourityService.findPage(queryPageBean);
        if (result == null) {
            new Result(false, "获取分页数据出错,请联系管理员");
        }
        return new Result(true, "获取分页数据成功", result);
    }


    @RequestMapping("/add")
    public Result addAthourity(@RequestBody Permission permission) {

        Boolean b=athourityService.addAthourity(permission);

        return new Result(b, b?"添加分页数据成功":"添加分页数据失败");
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {

       Permission permission =athourityService.findById(id);

        return new Result(true, "数据回显成功",permission);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Permission permission) {

        Boolean b =athourityService.update(permission);

        return new Result(b, b?"修改权限成功":"修改权限失败");
    }

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {

        Boolean b =athourityService.deleteById(id);

        return new Result(b, b?"删除权限成功":"删除权限失败,该权限被占用");
    }
}
