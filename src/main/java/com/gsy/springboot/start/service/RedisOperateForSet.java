package com.gsy.springboot.start.service;

/**
 * Created By Gsy on 2019/5/21
 */
public interface RedisOperateForSet {
    Object get(String key);
    void add(String key,Object value);
    Object pop(String key);
}
