package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.User;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/31
 */
public interface UserDao {
    /**
     * 通过用户名查询用户信息，包含角色及权限信息
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 添加用户信息
     * @param user
     */
    void add(User user);

    /**
     * 分页查询用户信息
     * @param queryString
     * @return
     */
    Page<User> findByCondition(String queryString);


    /**
     * 删除用户信息
     * @param id
     */
    void delete(int id);

    /**
     * 判断用户id是否被角色占用的方法
     * @param id
     * @return
     */
    int findByRole(int id);

    /**
     * 删除用户和角色的关系
     * @param id
     */
    void deleteUserRole(int id);

    /**
     * 修改用户信息
     * @param id
     */
    void update(User user);

    User findById(int id);
}
