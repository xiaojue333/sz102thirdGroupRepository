package com.itheima.health.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.AthourityDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Permission;
import com.itheima.health.service.AthourityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = AthourityService.class)
public class AthourityServiceImpl implements AthourityService {
    @Autowired
    private AthourityDao athourityDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        String String =queryPageBean.getQueryString();
        Page page =null;
        String queryString=null;
        if (!StringUtils.isEmpty(String)){
             queryString ="%"+String+"%";
        }
        page = athourityDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Boolean addAthourity(Permission permission) {
      boolean b=  athourityDao.addAthourity(permission);
        return b;
    }

    @Override
    public Permission findById(Integer id) {
        Permission permission=   athourityDao.findById(id);
        return permission;
    }

    @Override
    public Boolean update(Permission permission) {
        boolean b=  athourityDao.update(permission);
        return b;
    }

    @Override
    public Boolean deleteById(Integer id) {
       Integer i= athourityDao.qureyById(id);
        boolean b=false;
       if (i==0){
          b=  athourityDao.deleteById(id);
           return b;
       }

        return b;
    }

    @Override
    public List<Permission> findAll() {
        List<Permission> list=  athourityDao.findAll();
        return list;
    }
}
