package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.health.dao.MenuDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService {
    @Resource(name = "menuDao")
    private MenuDao menuDao;

    //菜单分页与条件查询
    @Override
    public PageResult<Menu> menuFindByPage(QueryPageBean pagination) {
        //使用分页查询分页查询 设置页码和每页数 下一次查询会被分页
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());

        //判定条件是否为空
        if (StringUtil.isNotEmpty(pagination.getQueryString())) {
            pagination.setQueryString("%" + pagination.getQueryString() + "%");

        }

        //调用dao层分页条件查询菜单数据
        Page<Menu> page = menuDao.menuFindByPage(pagination.getQueryString());
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    //查询所有一级菜单
    @Override
    public PageResult<Menu> findAllMainMenu(QueryPageBean pagination) {
        //使用分页查询分页查询 设置页码和每页数 下一次查询会被分页
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());

        //调用dao层分页条件查询菜单数据
        Page<Menu> page = menuDao.findAllMainMenu();

        return new PageResult<>(page.getTotal(), page.getResult());
    }

    //分页根据一级菜单id获取二级菜单
    @Override
    public PageResult<Menu> findMenuByMainId(QueryPageBean pagination, Integer id) {
        //使用分页查询分页查询 设置页码和每页数 下一次查询会被分页
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());

        //调用dao层分页条件查询菜单数据
        Page<Menu> page = menuDao.findMenuByMainId(id);

        return new PageResult<>(page.getTotal(), page.getResult());
    }

    //删除指定id菜单
    @Transactional
    @Override
    public void deleteById(Integer id) throws MyException {
        //判定当前id菜单是否有子菜单,有子菜单则不能删除
        List<Menu> rows = menuDao.findMenuByMainId(id);

        if (rows != null && rows.size() > 0) {//是查询到数据
            throw new MyException("无法删除有子菜单的菜单");
        }

        //删除与当前菜单id绑定的角色关系表中的数据
        menuDao.deleteMenuAndRoleById(id);

        //根据id删除当前菜单
        menuDao.deleteById(id);
    }

    //根据id查询菜单
    @Override
    public Menu findById(Integer id) {
        return menuDao.findById(id);
    }

    //获取所有菜单
    @Override
    public List<Menu> findAllMenu() {
        return menuDao.findAllMenu();
    }

    //添加菜单
    @Override
    public void addMenu(Menu menu) throws MyException {
        //补充路由路径与菜单层级
        Integer parentMenuId = menu.getParentMenuId();
        if (parentMenuId != null) {//根据父菜单id查询父菜单
            Menu parentMenu = menuDao.findById(parentMenuId);
            if (parentMenu == null) {
                throw new MyException("错误的父菜单");
            }
            //根据父菜单路径设置path与level等级
            String path = parentMenu.getPath();
            if (!path.contains("/")) {
                path = "/" + path;
            }

            //根据父菜单id获取当前子菜单最大优先级
            Integer maxPriority = menuDao.findMaxPriorityByParentMenuId(parentMenuId);
            if (maxPriority == null) {//没数据则为第一个
                maxPriority = 0;
            }
            menu.setPriority(maxPriority + 1);
            menu.setPath(path + "-" + (maxPriority + 1));
            menu.setLevel(parentMenu.getLevel() + 1);

            //添加菜单
            menuDao.addMenu(menu);
        } else {//没有父菜单则指定为主菜单
            Integer priority = menu.getPriority();
            if (priority == null) {
                //获取当前最大路径值
                String maxPath = menuDao.findMaxPath();
                int pri = Integer.parseInt(maxPath);
                menu.setPath(pri + 1 + "");//设置路径
                menu.setPriority(pri);//设置优先级
            }

            //设置为1级菜单
            menu.setLevel(1);
            //添加菜单
            menuDao.addMenu(menu);
        }
    }

    //更新菜单
    @Override
    public void updateMenu(Menu menu) throws MyException {
        //判定当前id菜单是否有子菜单,有子菜单则不能删除
        List<Menu> rows = menuDao.findMenuByMainId(menu.getId());

        if (rows != null && rows.size() > 0) {//是查询到数据
            if (menu.getParentMenuId() != null) {
                throw new MyException("无法修改有子菜单的菜单为子菜单");
            }
        }

        menuDao.updateMenu(menu);
    }
}
