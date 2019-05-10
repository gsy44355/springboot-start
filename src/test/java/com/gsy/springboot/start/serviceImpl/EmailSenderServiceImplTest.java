package com.gsy.springboot.start.serviceImpl;

import com.gsy.springboot.start.service.EmailSenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
//    @Ignore
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
    public void sendByTemplate(){
        String receiver = "18117835413@163.com";
        Map map = new HashMap();
        map.put("user","gsy");
        map.put("code","113344");
        boolean a = emailSenderService.sendByTemplate(receiver, "验证码", "emailTemplate/identifyCodeTemplate", map);
        assertTrue(a);
    }
}