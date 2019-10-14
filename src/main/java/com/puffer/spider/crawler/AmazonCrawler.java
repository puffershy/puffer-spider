package com.puffer.spider.crawler;

import com.puffer.spider.common.Constants;
import com.puffer.spider.entity.AmazonItem;
import com.puffer.spider.processor.AmazonItemPiperline;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import javax.annotation.Resource;

/**
 * 亚马逊爬虫
 *
 * @author buyi
 * @date 2019年10月14日 23:27:37
 * @since
 */
@Component
public class AmazonCrawler {

    @Resource
    private AmazonItemPiperline amazonItemPiperline;

    @Resource
    private BaseCrawler baseCrawler;

    public void amazonCrawler(String url){
        baseCrawler.crawler(
                amazonItemPiperline,
                new HttpClientDownloader(),
                url,
                Constants.THREADNUM,
                AmazonItem.class
        );
    }
}
