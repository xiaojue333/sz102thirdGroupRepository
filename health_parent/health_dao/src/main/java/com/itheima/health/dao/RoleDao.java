package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleDao {

    void add(Role role);


    void addRolePermission(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);


    Page<Role> findByCondition(String queryString);

    Role findById(int id);


    List<Integer> findPermissionIdsByRoleId(int id);


    void update(Role role);


    void deleteRolePermission(Integer id);


    int findCountByRoleId(int id);


    void deleteById(int id);


    List<Role> findAll();
}
