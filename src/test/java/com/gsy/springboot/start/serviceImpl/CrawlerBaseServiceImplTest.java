package com.gsy.springboot.start.serviceImpl;

import com.gsy.springboot.start.pojo.TbCrawlerUrl;
import com.gsy.springboot.start.service.crawler.CrawlerBaseService;
import com.gsy.springboot.start.service.crawler.WeiboCrawlerService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created By Gsy on 2019/5/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerBaseServiceImplTest {
    @Autowired
    CrawlerBaseService crawlerBaseService;
    @Autowired
    WeiboCrawlerService weiboCrawlerService;
    @Test
    @Ignore
    public void getUrl() {
        String s = crawlerBaseService.getUrl("1");
        System.out.println(s);
    }

    @Test
    public void addUrl() {
        TbCrawlerUrl t = new TbCrawlerUrl();
        t.setUrl("dddddd");
        t.setType("1");
        t.setBusy("0");
        int a = crawlerBaseService.addUrl(t);
        System.out.println(a);
    }

    @Test
    public void deleteUrl() {
        TbCrawlerUrl t = new TbCrawlerUrl();
        t.setUrl("dddddd");
        t.setType("1");
        t.setBusy("0");
        int a = crawlerBaseService.deleteUrl("dddddd");
        System.out.println(a);
    }

    @Test
    public void testDeleteAll(){
        int a = crawlerBaseService.deleteAll();
        System.out.println(a);
    }
    @Test
    public void start(){
        weiboCrawlerService.startNew();
    }
    @Test
    public void reStart(){
//        try {
//            WebCrawlerUtil.getWebPicture("http://img1.mm131.me/pic/4953/3.jpg", "1.jpg",CreateHeaderMap.getMapByNameWithRandomIp("crawler/picture1"),"");
//        } catch (IOException e) {
//            System.out.println("走到了异常");
//            e.printStackTrace();
//        }
        weiboCrawlerService.reStart();
    }
}