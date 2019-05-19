package com.gsy.springboot.start.controller;

import com.gsy.springboot.start.service.EmailSenderService;
import com.gsy.springboot.start.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By Gsy on 2019/5/3
 */
@RestController
public class TestRestController {
    @Autowired
    TestService testService;
    @Autowired
    EmailSenderService emailSenderService;
    @GetMapping("/test")
    public Object test(){
        return testService.testMapper();
    }
    @GetMapping("/error")
    public String error(){
        return "error"+new Date();
    }
    @GetMapping("/sendEmailTest")
    public String sendEmailTest(){
        String receiver = "443554017@qq.com";
        Map map = new HashMap();
        map.put("user","gsy");
        map.put("code","113344");
        boolean a = emailSenderService.sendByTemplate(receiver, "验证码", "emailTemplate/identifyCodeTemplate", map);
        return a?"success111":"fail";
    }

    @GetMapping("/testurl")
    public String testUrl(){
        testService.testaa();
        return "1";
    }

}
