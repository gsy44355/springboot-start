package com.gsy.springboot.start.pojo;

import javax.persistence.*;

@Table(name = "tb_crawler_url")
public class TbCrawlerUrl {
    /**
     * URL
     */
    @Id
    @Column(name = "URL")
    private String url;

    /**
     * Url类型
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 是否正在使用，0未使用，1正在使用
     */
    @Column(name = "BUSY")
    private String busy;

    /**
     * 备注
     */
    @Column(name = "INFO")
    private String info;

    /**
     * 获取URL
     *
     * @return URL - URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     *
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取Url类型
     *
     * @return TYPE - Url类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置Url类型
     *
     * @param type Url类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取是否正在使用，0未使用，1正在使用
     *
     * @return BUSY - 是否正在使用，0未使用，1正在使用
     */
    public String getBusy() {
        return busy;
    }

    /**
     * 设置是否正在使用，0未使用，1正在使用
     *
     * @param busy 是否正在使用，0未使用，1正在使用
     */
    public void setBusy(String busy) {
        this.busy = busy;
    }

    /**
     * 获取备注
     *
     * @return INFO - 备注
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置备注
     *
     * @param info 备注
     */
    public void setInfo(String info) {
        this.info = info;
    }
    public TbCrawlerUrl(){

    }

    public TbCrawlerUrl(String url, String type, String busy) {
        this.url = url;
        this.type = type;
        this.busy = busy;
    }

    public TbCrawlerUrl(String url, String busy) {
        this.url = url;
        this.busy = busy;
    }

}