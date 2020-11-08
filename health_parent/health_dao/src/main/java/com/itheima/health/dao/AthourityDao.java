package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Permission;

import java.util.List;

public interface AthourityDao {
    Page findPage(String s);

    boolean addAthourity(Permission permission);

    Permission findById(Integer id);

    boolean update(Permission permission);
    Integer qureyById(Integer id);
    boolean deleteById(Integer id);


    List<Permission> findAll();

}
