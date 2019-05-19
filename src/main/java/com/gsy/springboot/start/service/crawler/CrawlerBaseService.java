package com.gsy.springboot.start.service.crawler;

import com.gsy.springboot.start.pojo.TbCrawlerUrl;
import com.gsy.springboot.start.util.crawler.CrawlerSpecialFunc;

/**
 * Created By Gsy on 2019/5/18
 */
public interface CrawlerBaseService {
    /**
     *根据类型获取一个URL，并且将busy位置为1
     * @param type Url类型
     * @return
     */
    String getUrl(String type);

    /**
     * 将一个Url的busy位归0，这个方法不可以做成事务回滚，目前没有找到合适的方法
     * 所以只能在catch到exception之后调用该方法保证Url的正确完整性
     * @param url Url链接
     * @return
     */
    int updateUrlToNoUse(String url);

    /**
     * 将所有busy更新为0，在续传的时候调用
     * @return
     */
    int resetAll();

    /**
     * 添加一个Url进入Url池，设置好类型，等待线程爬取
     * @param tbCrawlerUrl tbCrawlerUrl对象，需要制定type，建议直接写pojo类三个参数的构造方法
     * @return
     */
    int addUrl(TbCrawlerUrl tbCrawlerUrl);

    /**
     * 删除一条Url，在这条Url已经完全被爬取之后再删除
     * @param url 要删除的Url
     * @return 删除条目，1正常返回
     */
    int deleteUrl(String url);

    /**
     * 开始新的爬虫时首先调用该方法，但是我觉得这个方法应该有一个type限制，否则如果多个不同的爬虫并行会很麻烦
     * @return 删除条目
     */
    int deleteAll();

    /**
     * 单线程crawler<br/>
     * 这个里面是一个while true循环，只有当数据库中没有这个type的Url之后才会break
     * 传入一个函数式接口，建议使用λ表达式，会代码简洁很多<br/>
     * 里面已经实现获取链接，删除链接，函数式接口中实现自己的操作。
     * @param type 链接类型
     * @param sleepTime 每一次爬取之后停止时间，微博这种你要是敢一直不停，就得无休止的改header
     * @param crawlerSpecialFunc 具体特殊操作细节，λ函数传入
     */
    void doCrawler(String type,long sleepTime,CrawlerSpecialFunc crawlerSpecialFunc);
    /**
     * 多线程crawler<br/>
     * 这个里面是一个while true循环，只有当数据库中没有这个type的Url之后才会break
     * 传入一个函数式接口，建议使用λ表达式，会代码简洁很多<br/>
     * 里面已经实现获取链接，删除链接，函数式接口中实现自己的操作。
     * @param type 链接类型
     * @param sleepTime 每一次爬取之后停止时间，微博这种你要是敢一直不停，就得无休止的改header
     * @param threadCounts 线程数量
     * @param crawlerSpecialFunc 具体特殊操作细节，λ函数传入
     */
    void doCrawlerByMultiThread(String type,long sleepTime,int threadCounts,CrawlerSpecialFunc crawlerSpecialFunc);


}
