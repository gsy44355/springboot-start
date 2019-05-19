package com.gsy.springboot.start.mapper.custom;

import com.gsy.springboot.start.pojo.TbCrawlerUrl;

/**
 * Created By Gsy on 2019/5/18
 */
public interface TbCrawlerUrlCustomMapper {
    TbCrawlerUrl getOneUrl(String type);
    int deleteAll();
    int resetAll();
}
