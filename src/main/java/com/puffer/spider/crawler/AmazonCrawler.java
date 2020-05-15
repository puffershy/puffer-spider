package com.puffer.spider.crawler;

import com.puffer.spider.common.constants.Constants;
import com.puffer.spider.entity.AmazonItem;
import com.puffer.spider.processor.AmazonItemPiperline;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import javax.annotation.Resource;

/**
 * 亚马逊爬虫
 *
 * @author puffer
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

        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("",)));

        baseCrawler.crawler(
                amazonItemPiperline,
                httpClientDownloader,
                url,
                Constants.THREADNUM,
                AmazonItem.class
        );
    }
}
