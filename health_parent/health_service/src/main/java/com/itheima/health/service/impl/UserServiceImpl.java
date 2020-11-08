package com.itheima.health.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.UserDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Menu;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/31
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public PageResult<User> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 模糊查询分页
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            // 拼接%
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString()+ "%");
        }
        // 调用方法查询
        Page<User> page = userDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<User>(page.getTotal(), page.getResult());
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void delete(int id) {

        //判断用户id是否被角色占用的方法
        int cnt = userDao.findByRole(id);
        if(cnt > 0){
            //删除用户和角色的关系
            userDao.deleteUserRole(id);
        }
        //调用Dao层删除
        userDao.delete(id);
    }


    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * 通过用户名查询用户信息，包含角色及权限信息
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }


    /**
     * 添加用户信息
     * @param user
     */
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    /**
     * 根据用户名去查询用户权限菜单集合
     *
     * @param username
     * @return
     */
    @Override
    public List<Menu> getMenuList(String username) {

        //查询主菜单
        List<Menu> MainMenuList = userDao.getMainMenuList(username);
        //结果集合
        List<Menu> resultMenuList = new ArrayList<>();
        //遍历所有菜单
        for (Menu menu : MainMenuList) {
            //判断是否是父菜单
            if (menu.getParentMenuId() == null) {
                //是 添加进集合
                resultMenuList.add(menu);
            }
            //子菜单集合
            List<Menu> secondMenuList = new ArrayList<>();
            //循环添加子菜单
            for (Menu menu1 : MainMenuList) {
                if (menu1.getParentMenuId() != null && menu1.getParentMenuId() == menu.getId()) {
                    secondMenuList.add(menu1);
                }
            }
            menu.setChildren(secondMenuList);
        }
        return resultMenuList;
    }
}
