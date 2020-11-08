package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.RoleDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    @Transactional
    public void add(Role role, Integer[] permissionIds) {
        // 先添加角色
        roleDao.add(role);
        // 角色id
        Integer roleId = role.getId();
        // 遍历选中的权限ids
        if(null != permissionIds) {
            // 添加角色与权限的关系
            for (Integer permissionId : permissionIds) {
                roleDao.addRolePermission(roleId,permissionId);
            }
        }
    }


    @Override
    public PageResult<Role> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+ queryPageBean.getQueryString()+"%");
        }
        Page<Role> page = roleDao.findByCondition(queryPageBean.getQueryString());
        PageResult<Role> pageResult = new PageResult<Role>(page.getTotal(),page.getResult());
        return pageResult;
    }


    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }


    @Override
    public List<Integer> findPermissionIdsByRoleId(int id) {
        return roleDao.findPermissionIdsByRoleId(id);
    }


    @Transactional
    @Override
    public void update(Role role, Integer[] permissionIds) {
        // 更新角色
        roleDao.update(role);
        // 删除旧关系
        roleDao.deleteRolePermission(role.getId());
        // 添加新关系
        if(null != permissionIds){
            for (Integer permissionId : permissionIds) {
                roleDao.addRolePermission(role.getId(), permissionId);
            }
        }
    }


    @Transactional
    @Override
    public void deleteById(int id) throws MyException {

        int count = roleDao.findCountByRoleId(id);

        if(count > 0){
            throw new MyException("该角色有指定用户，不能删除");
        }


        roleDao.deleteRolePermission(id);

        roleDao.deleteById(id);
    }


    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
