package com.gsy.springboot.start.serviceImpl.crawler;

import com.gsy.springboot.start.mapper.custom.TbCrawlerUrlCustomMapper;
import com.gsy.springboot.start.mapper.auto.TbCrawlerUrlMapper;
import com.gsy.springboot.start.pojo.TbCrawlerUrl;
import com.gsy.springboot.start.service.crawler.CrawlerBaseService;
import com.gsy.springboot.start.util.LogUtil;
import com.gsy.springboot.start.util.crawler.CrawlerSpecialFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Gsy on 2019/5/18
 */
@Service
public class CrawlerBaseServiceImpl implements CrawlerBaseService {
    @Autowired
    TbCrawlerUrlMapper tbCrawlerUrlMapper;
    @Autowired
    TbCrawlerUrlCustomMapper tbCrawlerUrlCustomMapper;
    @Override
    public synchronized String getUrl(String type) {
        TbCrawlerUrl tbCrawlerUrl = tbCrawlerUrlCustomMapper.getOneUrl(type);
        if (tbCrawlerUrl == null){
            return null;
        }
        tbCrawlerUrl.setBusy("1");
        tbCrawlerUrlMapper.updateByPrimaryKeySelective(tbCrawlerUrl);
        return tbCrawlerUrl.getUrl();
    }

    @Override
    public int updateUrlToNoUse(String url) {
        return tbCrawlerUrlMapper.updateByPrimaryKeySelective(new TbCrawlerUrl(url,"0"));
    }

    @Override
    public int resetAll() {
        tbCrawlerUrlCustomMapper.resetAll();
        return 1;
    }

    @Override
    public int addUrl(TbCrawlerUrl tbCrawlerUrl) {
        try{
            return tbCrawlerUrlMapper.insertSelective(tbCrawlerUrl);
        }catch (DuplicateKeyException e){
            LogUtil.info(this.getClass(),"Crawler获取到重复Url={}",tbCrawlerUrl.getUrl());
            return 1;
        }
    }

    @Override
    public int deleteUrl(String url) {
        return tbCrawlerUrlMapper.deleteByPrimaryKey(url);
    }

    @Override
    public int deleteAll() {
        return tbCrawlerUrlCustomMapper.deleteAll();

    }

    @Override
    public void doCrawler(String type,long sleepTime,CrawlerSpecialFunc crawlerSpecialFunc) {
        int errorCount = 0;
        while(true){
            String url = null;
            try {
                if(sleepTime != 0 ){
                    Thread.sleep(sleepTime);
                }
                url = this.getUrl(type);
                if(url == null){
                    break;
                }
                LogUtil.info(this.getClass(),"获取到Url={}",url);
                crawlerSpecialFunc.specialFunc(url);
                this.deleteUrl(url);
            }catch (Exception e){
                errorCount++;
                LogUtil.error(this.getClass(),"抓取异常，决定需要如何处理",e);
                this.updateUrlToNoUse(url);
                if (errorCount >100){
                    break;
                }
            }
        }
    }

    @Override
    public void doCrawlerByMultiThread(String type, long sleepTime, int threadCounts, CrawlerSpecialFunc crawlerSpecialFunc) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCounts; i++) {
            LogUtil.info(this.getClass(),"创建线程={}",""+i);
            Thread thread = new Thread(() -> {
                doCrawler(type,sleepTime,crawlerSpecialFunc);
            });
            list.add(thread);
            thread.start();
        }
        for (Thread thread:list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
