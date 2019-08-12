package com.itheima.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.service.OrderSettingService;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;


/**
 * 定时清理预约设置历史数据
 *
 * 每个月的最后一天的23点59分 cron表达式= "0 59 23 L * ?"
 */
@Component
public class CronJob{

    @Reference
    private OrderSettingService orderSettingService;

    /**
     * 每个月的最后一天的23点59分执行
     * 定时清理OrderSetting表中废旧数据
     */
    public void deleteOrderSetting(){
        System.out.println("方法执行了...");
        try {
            orderSettingService.deleteOrderSetting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
