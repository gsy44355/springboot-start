package com.gsy.springboot.start.service;

import com.gsy.springboot.start.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created By Gsy on 2019/5/1
 */
@Service
public class TestService {
    @Autowired
    TestMapper userInfoMapper;
    public Map testMapper(){
        return userInfoMapper.test();
    }



}
