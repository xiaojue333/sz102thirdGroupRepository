package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.User;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/31
 */
public interface UserService {

    /**
     * 通过用户名查询用户信息，包含角色及权限信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 添加用户信息
     * @param user
     */
    void add(User user);

    /**
     * 分页查询
     */
    PageResult<User> findPage(QueryPageBean queryPageBean);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(int id);

    /**
     * 修改用户信息
     * @param user
     */
    void update(User user);

    /**
     * 回显数据的方法
     * @param id
     * @return
     */
    User findById(int id);
}
