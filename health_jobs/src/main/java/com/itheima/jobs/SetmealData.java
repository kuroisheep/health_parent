package com.itheima.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

public class SetmealData {

    @Autowired
    private JedisPool jedisPool;



    /**
     * 将套餐信息放入redis缓存中提高查询性能
     */
    private void setmealData(){


    }
}
