package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Menu;

import java.util.List;

public interface MenuDao {

    //分页条件查询菜单数据
    Page<Menu> menuFindByPage(String queryString);

    //分页查询所有一级菜单
    Page<Menu> findAllMainMenu();

    //分页根据一级菜单id获取二级菜单
    Page<Menu> findMenuByMainId(Integer id);

    //删除与当前菜单id绑定的角色关系表中的数据
    void deleteMenuAndRoleById(Integer id);

    //根据id删除当前菜单
    void deleteById(Integer id);

    //根据id查询菜单
    Menu findById(Integer id);

    //获取所有菜单
    List<Menu> findAllMenu();

    //获取当前最大路径值
    String findMaxPath();

    //添加菜单
    void addMenu(Menu menu);

    //根据父菜单id获取当前子菜单最大优先级
    Integer findMaxPriorityByParentMenuId(int parentMenuId);

    //更新菜单
    void updateMenu(Menu menu);
}
