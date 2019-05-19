package com.gsy.springboot.start.util.crawler;

/**
 * Created By Gsy on 2019/5/18
 * 在doCrawler方法中作为参数传入，实现对于这个Url的定制化操作
 */
@FunctionalInterface
public interface CrawlerSpecialFunc {
    void specialFunc(String url) throws Exception;
}
