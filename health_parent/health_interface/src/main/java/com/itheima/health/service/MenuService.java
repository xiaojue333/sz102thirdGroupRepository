package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Menu;

import java.util.List;

public interface MenuService {

    //菜单分页与条件查询
    PageResult<Menu> menuFindByPage(QueryPageBean pagination);

    //分页查询所有一级菜单
    PageResult<Menu> findAllMainMenu(QueryPageBean pagination);

    //分页根据一级菜单id获取二级菜单
    PageResult<Menu> findMenuByMainId(QueryPageBean pagination, Integer id);

    //删除指定id菜单
    void deleteById(Integer id) throws MyException;

    //根据id查询菜单
    Menu findById(Integer id);

    //获取所有菜单
    List<Menu> findAllMenu();

    //添加菜单
    void addMenu(Menu menu) throws MyException;

    //更新菜单
    void updateMenu(Menu menu) throws MyException;
}
