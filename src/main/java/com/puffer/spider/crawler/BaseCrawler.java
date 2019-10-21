package com.puffer.spider.crawler;

import com.puffer.spider.common.constants.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;


@Component
public class BaseCrawler {
    private Logger logger = Logger.getLogger(getClass());

    /**
     * General crawler entrance
     *
     * @param pageModelPipeline
     * @param downloader
     * @param seed
     * @param thread
     * @param clazz
     */
    public void crawler(PageModelPipeline pageModelPipeline, Downloader downloader, String seed, int thread, Class clazz) {
        logger.info("started to collect the data of module");
        // Each module timing
        // startMili:The number of milliseconds the current time
        long startMili = System.currentTimeMillis();
        //Create the crawler
        OOSpider.create(
                Site.me().setUserAgent(Constants.USERAGENT),
                pageModelPipeline, clazz)
                .setDownloader(downloader)
                .addUrl(seed)
                .thread(thread)
                .run();
        //The end of time
        long endMili = System.currentTimeMillis();
        logger.info("elapsed time:" + (endMili - startMili) + "ms");
        logger.info("collect the data of module end");
    }
}
