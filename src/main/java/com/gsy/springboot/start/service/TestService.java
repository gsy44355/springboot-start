package com.gsy.springboot.start.service;

import com.gsy.springboot.start.mapper.custom.TestMapper;
import com.gsy.springboot.start.pojo.TbCrawlerUrl;
import com.gsy.springboot.start.service.crawler.CrawlerBaseService;
import com.gsy.springboot.start.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created By Gsy on 2019/5/1
 */
@Service
public class TestService {
    @Autowired
    TestMapper userInfoMapper;
    @Autowired
    CrawlerBaseService crawlerBaseService;
    public Map testMapper(){
        return userInfoMapper.test();
    }
    @Async
    public void testaa(){
        String url = crawlerBaseService.getUrl("1");
        LogUtil.info(this.getClass(),"urlshi={}",url);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        crawlerBaseService.addUrl(new TbCrawlerUrl("eeeeee","1","0"));
//        throw new RuntimeException("测试");
    }
    @Async
    public void testbb(){
        String url = crawlerBaseService.getUrl("1");
        System.out.println(url);
    }

//    public OutputStream cmd(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
//        Process process = Runtime.getRuntime().exec("cmd");
//
//        process.getOutputStream();
//    }



}
