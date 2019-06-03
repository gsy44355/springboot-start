package com.gsy.springboot.start.serviceImpl;

import com.gsy.springboot.start.service.RedisOperateForSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created By Gsy on 2019/5/21
 */
public class RedisOperateForSetImpl implements RedisOperateForSet {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void add(String key, Object value) {

    }

    @Override
    public Object pop(String key) {
        return null;
    }
}
