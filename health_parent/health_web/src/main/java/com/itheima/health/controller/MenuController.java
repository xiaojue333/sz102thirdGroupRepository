package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    /**
     * @return 总行数 每页的菜单数据
     * 分页获取菜单
     */
    @PostMapping("/findAllMenu")
    public Result findAllMenu(@RequestBody QueryPageBean pagination) {
        PageResult<Menu> menus = menuService.menuFindByPage(pagination);
        return new Result(true, MessageConstant.GET_MENU_SUCCESS, menus);
    }

    /**
     * @return 总行数 每页的菜单数据
     * 获取所有主菜单
     */
    @PostMapping("/findAllMainMenu")
    public Result mainMenuFindByPage(@RequestBody QueryPageBean pagination) {
        PageResult<Menu> menus = menuService.findAllMainMenu(pagination);
        return new Result(true, MessageConstant.GET_MENU_SUCCESS, menus);
    }

    /**
     * @param id 上级菜单id
     * @return 当前菜单数据
     * 获取主id下所有二级菜单
     */
    @PostMapping("/findMenuByMainId")
    public Result findMenuByMainId(@RequestBody QueryPageBean pagination, Integer id) {
        PageResult<Menu> menus = menuService.findMenuByMainId(pagination, id);
        return new Result(true, MessageConstant.GET_MENU_SUCCESS, menus);
    }

    /**
     * @param id 删除菜单id
     * @return 成功提示
     * 删除指定id菜单
     */
    @PostMapping("/deleteById")
    public Result deleteById(Integer id) {
        menuService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_MENU_SUCCESS);
    }

    /**
     * @return 返回菜单数据
     * 根据id查询菜单
     */
    @GetMapping("/findById")
    public Result findById(Integer id) {
        Menu menu = menuService.findById(id);
        return new Result(true, MessageConstant.GET_MENU_SUCCESS, menu);
    }

    /**
     * @return 所有菜单
     * 获取所有菜单
     */
    @GetMapping("/findAllMenu")
    public Result findAllMenu() {
        List<Menu> menus = menuService.findAllMenu();
        return new Result(true, MessageConstant.GET_MENU_SUCCESS, menus);
    }

    /**
     * @param menu 菜单对象
     * @return 成功信息
     * 添加菜单
     */
    @PostMapping("/addMenu")
    public Result addMenu(@RequestBody Menu menu) {
        menuService.addMenu(menu);
        return new Result(true, MessageConstant.ADD_MENU_SUCCESS);
    }

    /**
     * @param menu 菜单对象
     * @return 成功信息
     * 编辑菜单
     */
    @PostMapping("/updateMenu")
    public Result updateMenu(@RequestBody Menu menu) {
        menuService.updateMenu(menu);
        return new Result(true, MessageConstant.UPDATE_MENU_SUCCESS);
    }
}
