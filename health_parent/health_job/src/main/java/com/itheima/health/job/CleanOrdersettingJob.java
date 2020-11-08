package com.itheima.health.job;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.service.OrderSettingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class CleanOrdersettingJob {

    @Reference
    private OrderSettingService orderSettingService;

    //每隔30天执行一次清理旧预约数据
    //@Scheduled(initialDelay = 10000,fixedDelay = 30*24*3600)
    //每月1号凌晨(00:00:00)执行一次清理旧预约数据
    @Scheduled(cron = "0 0 0 1 * ? ")
    public void cleanOrdersetting(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Calendar currentTime = Calendar.getInstance();
        //获取清理预约设置的日期(保留一个月的旧数据)
        currentTime.add(Calendar.MONTH,-1);
        Date time = currentTime.getTime();
        String cleanTime = sdf.format(time);
        //调用服务层
        orderSettingService.cleanOrdersetting(cleanTime);

    }
}
