package com.gsy.springboot.start.serviceImpl;

import com.gsy.springboot.start.service.EmailSenderService;
import com.gsy.springboot.start.util.LogUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created By Gsy on 2019/5/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSenderServiceImplTest {
    @Autowired
    EmailSenderService emailSenderService;
    @Test
    public void send() {
        boolean a;
        String receiver = "443554017@qq.com";
        String cc = "443554017@qq.com";
        String[] ccs = {"443554017@qq.com","1092129665@qq.com"};
        String[] receivers = {"443554017@qq.com","1092129665@qq.com"};
        String[] files = {"HELP.md"};
        a = emailSenderService.send(receiver,"test","测试邮件");
        assertTrue(a);
        a = emailSenderService.send(receivers,"test","测试邮件");
        assertTrue(a);
        a = emailSenderService.send(receiver,"test","测试邮件",cc);
        assertTrue(a);
        a = emailSenderService.send(receiver,"test","测试邮件",ccs);
        assertTrue(a);
        a = emailSenderService.send(receivers,"test","测试邮件",cc);
        assertTrue(a);
        a = emailSenderService.send(receivers,"test","测试邮件",ccs);
        assertTrue(a);
        a = emailSenderService.send(receivers,"test","测试邮件",ccs,files);
        assertTrue(a);

    }
    @Test
    @Ignore
    public void sendByTemplate(){
        String receiver = "18117835413@163.com";
        Map map = new HashMap();
        map.put("user","gsy");
        map.put("code","113344");
        boolean a = emailSenderService.sendByTemplate(receiver, "验证码", "emailTemplate/identifyCodeTemplate", map);
        assertTrue(a);
    }
    @Test
    public void test(){
        long a  =  System.currentTimeMillis();
        for (int i = 0; i <1000000000 ; i++) {
            LoggerFactory.getLogger(this.getClass());
//            logger.info("aaa={}","bbb");
        }
        System.out.println(System.currentTimeMillis() - a);
        a  =  System.currentTimeMillis();
        for (int i = 0; i <1000000000 ; i++) {
//            LogUtil.info(this.getClass(),"aaa={}","bbb");
            LogUtil.getLogger(this.getClass());
        }
        System.out.println(System.currentTimeMillis() - a);
    }
}