package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.Utils.QiNiuUtils;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/28
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {

    @Reference
    private SetmealService setmealService;

    /**
     * 套餐列表
     * @return
     */
     @GetMapping("/getSetmeal")
    public Result getSetmeal(){
        // 套餐列表
        //从工具类里获得jedis
        Jedis jedis = JedisUtils.getConn();
        //先从redis里获取数据(存储json格式串到redis),redis里没有在从Mysql里查
        String setmeal = jedis.get("setmealList");
        List<Setmeal> setmealList = null;
        if (setmeal != null) {
            //把json格式字符串转成List返回
            setmealList = JSON.parseObject(setmeal, List.class);
        } else {
            //没有,调用service层查,存到redis,再返回
          setmealList = setmealService.findAll();
            //将list转成json格式串存到redis
            jedis.set("setmealList", JSON.toJSONString(setmealList));
        }
        // 前端需要显示图片，要拼接图片的完整路径 java8 stream流操作
        setmealList.forEach(s -> s.setImg(QiNiuUtils.DOMAIN+s.getImg()));
        //for (Setmeal s : setmealList) {
        //    s.setImg(QiNiuUtils.DOMAIN+ s.getImg());
        //}
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmealList);
    }

    /**
     * 套餐详情 
     */
    @GetMapping("/findDetailById")
    public Result findDetailById(int id) {
        //从工具类里获得jedis
        Jedis jedis = JedisUtils.getConn();
        //先从redis里获取数据(存储json格式串到redis),redis里没有在从Mysql里查
        String setMealMobile_id = jedis.get("setMealMobile_" + id);
        Setmeal setmeal = null;
        if (setMealMobile_id != null) {
            //把json格式字符串转成List返回
            setmeal = JSON.parseObject(setMealMobile_id, Setmeal.class);
        } else {
            //没有,调用Service层查,存到redis,再返回
             setmeal = setmealService.findDetailById(id);
            //将list转成json格式串存到redis
        }
        // 调用服务查询
        // 图片的完整路径
        setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }
    /**
     * 套餐详情
     */
    @GetMapping("/findDetailById2")
    public Result findDetailById2(int id){
        // 调用服务查询
        Setmeal s = setmealService.findDetailById2(id);
        // 图片的完整路径
        s.setImg(QiNiuUtils.DOMAIN+s.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,s);
    }

    /**
     * 套餐详情
     */
    @GetMapping("/findDetailById3")
    public Result findDetailById3(int id){
        // 调用服务查询
        Setmeal s = setmealService.findDetailById3(id);
        // 图片的完整路径
        s.setImg(QiNiuUtils.DOMAIN+s.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,s);
    }

    /**
     * 套餐基本信息
     */
    @GetMapping("/findById")
    public Result findById(int id){
        // 调用服务查询
        Setmeal s = setmealService.findById(id);
        // 图片的完整路径
        s.setImg(QiNiuUtils.DOMAIN+s.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,s);
    }
}
