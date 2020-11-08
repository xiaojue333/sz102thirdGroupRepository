package com.itheima.health.service;


import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Permission;

import java.util.List;

public interface AthourityService {

    PageResult findPage(QueryPageBean queryPageBean);


    Boolean addAthourity(Permission permission);

    Permission findById(Integer id);

    Boolean update(Permission permission);

    Boolean deleteById(Integer id);

    List<Permission> findAll();

}
