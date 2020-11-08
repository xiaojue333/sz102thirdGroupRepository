package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;

import com.itheima.health.pojo.Role;

import java.util.List;


public interface RoleService {

    void add(Role role, Integer[] permissionIds);


    PageResult<Role> findPage(QueryPageBean queryPageBean);


    Role findById(int id);


    List<Integer> findPermissionIdsByRoleId(int id);


    void update(Role role, Integer[] permissionIds);


    void deleteById(int id) throws MyException;


    List<Role> findAll();
}
