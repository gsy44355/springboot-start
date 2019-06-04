package com.gsy.springboot.start.service;

import com.gsy.springboot.start.mapper.auto.TbSysUserMapper;
import com.gsy.springboot.start.util.LogUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created By Gsy on 2019/5/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {
    @Autowired
    TestService testService;
    @Autowired
    TbSysUserMapper tbSysUserMapper;
    @Test
    @Ignore
    public void testMapper() {
        System.out.println(testService.testMapper());
    }
    @Test
    public void test1(){
        System.out.println(tbSysUserMapper.selectAll());
    }
    @Test
    @Ignore
    public void testcc(){
        long a = System.currentTimeMillis();
        Set<Thread> set  = new HashSet<>();
        for (int i = 1; i <= 100; i++) {
            try {
                Class temp = Class.forName("com.gsy.springboot.start.service.a"+i);
                Thread thread = new Thread((Runnable) temp.newInstance());
                set.add(thread);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        for (Thread t:set) {
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - a);
    }
}