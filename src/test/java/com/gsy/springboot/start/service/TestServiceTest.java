package com.gsy.springboot.start.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created By Gsy on 2019/5/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {
    @Autowired
    TestService testService;
    @Test
    @Ignore
    public void testMapper() {
        System.out.println(testService.testMapper());
    }
    @Test
    public void testaa(){
        testService.testaa();
        testService.testbb();
    }
}